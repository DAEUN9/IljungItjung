FROM node:16-alpine as build-stage
WORKDIR /var/jenkins_home/workspace/IJIJ-front/front
COPY package*.json ./
RUN npm install --force
COPY . .
RUN npm run build
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /var/jenkins_home/workspace/IJIJ-front/front/build /usr/share/nginx/html
# COPY --from=build-stage /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]