FROM node:16-alpine as build-stage
WORKDIR /var/jenkins_home/workspace/IJIJ-front/front
COPY package*.json ./
RUN npm install --force
COPY . .
RUN npm run build
FROM nginx
COPY --from=build-stage /var/jenkins_home/workspace/IJIJ-front/front/dist /usr/share/nginx/html
EXPOSE 80