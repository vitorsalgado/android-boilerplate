FROM mhart/alpine-node:6.10.0

WORKDIR /app

COPY package.json .

RUN npm i --production

COPY . .

CMD npm start