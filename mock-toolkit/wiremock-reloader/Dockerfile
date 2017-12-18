FROM node:8.9.2

WORKDIR /app

COPY package.json package-lock.json .

RUN npm install --production

COPY . .

CMD npm start
