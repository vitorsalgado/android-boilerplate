FROM mhart/alpine-node:6.10.2

WORKDIR /app

COPY package.json .

RUN npm install --production

COPY . .

CMD npm start