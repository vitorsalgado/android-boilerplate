'use strict';

const Request = require('request-promise');
const Chokidar = require('chokidar');
const IP = require('ip');

const WIREMOCK_DATA = process.env.WIREMOCK_DATA || './data';
const WIREMOCK_HOST = process.env.WIREMOCK_HOST || 'wiremock';
const WIREMOCK_PORT = process.env.WIREMOCK_PORT || 3000;

const watcher = Chokidar.watch(WIREMOCK_DATA, { ignored: /[/\\]\./, persistent: true, ignoreInitial: true });
const exec = require('child_process').exec;

const onFileChange = (event, path) => {
    const valid = path && path.length > 5 && path.substr(path.length - 5) === '.json';

    if (!valid) {
        return;
    }

    console.log('\x1b[33mFile change detected. Reseting Wiremock mappings ...\x1b[0m');

    Request.post(`http://${WIREMOCK_HOST}:${WIREMOCK_PORT}/__admin/mappings/reset`)
        .then(() => console.log('\x1b[32mWiremock mappings reseted!\x1b[0m'))
        .catch(() => console.error('\x1b[31mWiremock is unavailable now!\x1b[0m'));
};

watcher
    .on('add', (path) => onFileChange('add', path))
    .on('change', (path) => onFileChange('change', path))
    .on('unlink', (path) => onFileChange('unlink', path));

console.log(`\x1b[32mWiremock Reloader ready.\nChange your API Base Uri to: http://${IP.address()}:${WIREMOCK_PORT}\x1b[0m`);
