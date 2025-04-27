FROM node:18-alpine AS build

WORKDIR /app

ARG REACT_APP_API_URL

#PRODUCTION k8s
ENV REACT_APP_LOGIN_SERVICE=${REACT_APP_API_URL}
ENV REACT_APP_PANEL_SERVICE=${REACT_APP_API_URL}

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine AS production

COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]