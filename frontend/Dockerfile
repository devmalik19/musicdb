FROM node:18.17.1 as node
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
RUN npm install
RUN npm run build
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /usr/local/app/dist/music-db /usr/share/nginx/html
EXPOSE 80

