#!/bin/bash

export DOCKER_REPOSITORY="kacperklimas10"
export TAG="${1:-dev-multiarch}"
if [ "${TAG}" != "dev-multiarch" ]; then
      export FRONTED_DOCKERFILE="prod.Dockerfile"
  else
      export FRONTED_DOCKERFILE="dev.Dockerfile"
fi

function build() {
  checkBuilder=$(docker buildx inspect | grep "Name" | head -n 1 | awk '{print $2}')
  if [ "${checkBuilder}" = "container-builder" ]; then
    docker buildx build -t "${DOCKER_REPOSITORY}/serwis_logowania:${TAG}" \
      --platform linux/amd64,linux/arm64 --push ./serwis_logowania && \
    docker buildx build -t "${DOCKER_REPOSITORY}/serwis_panel:${TAG}" \
      --platform linux/amd64,linux/arm64 --push ./serwis_panel && \
    docker buildx build -t "${DOCKER_REPOSITORY}/serwis_frontend:${TAG}" \
      --platform linux/amd64,linux/arm64 --push -f ./frontend/"${FRONTED_DOCKERFILE}" ./frontend
  else
      docker buildx create \
        --name container-builder \
        --driver docker-container \
        --bootstrap --use && \
      build || return 1
  fi
}
if build; then
  echo "Project build successful"
  exit 0
else
  echo "Project build failed"
  exit 1
fi






