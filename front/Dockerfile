FROM node:16-alpine as build-stage
WORKDIR /var/jenkins_home/workspace/IJIJ-front/front
COPY package*.json ./
RUN npm install --force
COPY . .
EXPOSE 80
CMD ["npm", "run", "build;"]