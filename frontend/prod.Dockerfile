# Etap budowania aplikacji frontendowej przy użyciu lekkiego obrazu Node.js Alpine
FROM node:18-alpine AS build

WORKDIR /app

# Zmienna ARG do ustawienia adresu API (zastępowana przy budowie)
ARG REACT_APP_API_URL="http://localhost"

# Ustawienie endpointów usług jako jednej wartości (np. w Kubernetes będzie to adres Ingressa)
ENV REACT_APP_LOGIN_SERVICE=${REACT_APP_API_URL}
ENV REACT_APP_PANEL_SERVICE=${REACT_APP_API_URL}

# Skopiowanie plików zależności (npm
COPY package.json package-lock.json ./

# Instalacja zależności
RUN npm install

# Skopiowanie pozostałych plików aplikacji
COPY . .
RUN npm run build

# Drugi etap: użycie lekkiego obrazu nginx bez uprawnien roota jako serwera HTTP
FROM nginxinc/nginx-unprivileged:alpine3.21-perl AS production

# Skopiowanie zbudowanej aplikacji do katalogu obsługiwanego przez nginx
COPY --from=build /app/build /usr/share/nginx/html

# Adnotacja dla portu 8080 (na tym porcie będzie działał serwer nginx)
EXPOSE 8080

# Uruchomienie nginx przy starcie kontenera
CMD ["nginx", "-g", "daemon off;"]