#!/bin/bash

export WIREMOCK_HOST=localhost
export WIREMOCK_PORT=3001
export WIREMOCK_DATA=../wiremock/data

cd wiremock-reloader
npm install --production
npm start &

cd ../wiremock
java -jar wiremock.jar --root-dir data --port=${WIREMOCK_PORT} --verbose
