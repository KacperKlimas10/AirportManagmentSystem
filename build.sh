#!/bin/bash

export DOCKER_REPOSITORY="kacperklimas10"
export TAG="latest"

docker buildx create \
  --name container-builder \
  --driver docker-container \
  --bootstrap --use

docker buildx build -t ${DOCKER_REPOSITORY}/serwis_logowania:${TAG} \
  --platform linux/amd64,linux/arm64 --push ./serwis_logowania && \
docker buildx build -t ${DOCKER_REPOSITORY}/serwis_panel:${TAG} \
  --platform linux/amd64,linux/arm64 --push ./serwis_panel && \
docker buildx build -t ${DOCKER_REPOSITORY}/serwis_frontend:${TAG} \
  --platform linux/amd64,linux/arm64 --push ./frontend && \
echo "Project builded!"