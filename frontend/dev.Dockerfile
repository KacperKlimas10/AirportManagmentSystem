FROM node:18-alpine AS build

WORKDIR /app

ARG REACT_APP_API_URL

# DEV FOR COMPOSE
ENV REACT_APP_LOGIN_SERVICE="${REACT_APP_API_URL}:8081"
ENV REACT_APP_PANEL_SERVICE="${REACT_APP_API_URL}:8082"

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine AS production

RUN groupadd -r nginx && \
    useradd -r nginx -g nginx && \
    chown -R nginx:nginx /etc/nginx

COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

USER nginx

ENTRYPOINT ["nginx", "-g", "daemon off;"]