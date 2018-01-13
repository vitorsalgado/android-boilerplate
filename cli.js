'use strict';

const Inquirer = require('inquirer');
const FileSystem = require('fs');
const ChildProcess = require('child_process');

const exec = ChildProcess.exec;

Inquirer.prompt(
	[
		{
			type: 'input',
			name: 'project',
			message: 'What\'s the project name?',
			validate: (input) => !!input
		},
		{
			type: 'input',
			name: 'package',
			message: 'What\'s the project package name?',
			validate: (input) => !!input && input.indexOf('.') >= 0 && input !== 'com.example'
		}
	])
	.then(async (answers) => {
		const project = answers['project'];
		const pkg = answers['package'];
		const folders = pkg.replace(/\./g, '/');

		let pwd = await cmd('pwd');
		pwd = pwd.toString().replace('\n', '');

		const appBuildPath = `${pwd}/app/build.gradle`;
		const appBuild = FileSystem.readFileSync(appBuildPath).toString();
		const changedAppBuild = appBuild.replace(/final app_name = 'Boilerplate'/, `final app_name = '${project}'`);

		FileSystem.writeFileSync(appBuildPath, changedAppBuild);

		const root = `${pwd}`;
		const libraries = [
			`${root}/app`,
			`${root}/analytics`,
			`${root}/android-utils`,
			`${root}/api`,
			`${root}/graph-api`,
			`${root}/logger`,
			`${root}/interactors`,
			`${root}/uava`
		];
		const languages = [
			'java',
			'kotlin',
			'scala'
		];

		libraries.forEach(async (library) =>
			languages.forEach(async (language) =>
				FileSystem
					.readdirSync(`${library}/src`)
					.forEach(async (src) => {
						try {
							const dirs = FileSystem.readdirSync(`${library}/src/${src}/${language}`);

							if (dirs.length === 0) {
								return;
							}
						} catch (ex) {
							if (ex.code !== 'ENOENT') {
								throw ex;
							}

							return;
						}

						await cmd(`rm -rf ${library}/src/${src}/${language}/com`)
						await cmd(`cd ${library}/src/${src}/java && mkdir -p ${folders}`);
						await cmd(`mv ${library}/src/${src}/${language}/com/example/* ${library}/src/${src}/${language}/${folders}/`);

						readDirRecursively(`${library}/`, filters)
							.map((file) => Object.create({ content: FileSystem.readFileSync(file), file }))
							.map(({ content, file }) => Object.create({ content: content.toString(), file }))
							.map(({ content, file }) => Object.create({
								content: content.split('com.example').join(pkg),
								file
							}))
							.forEach(({ content, file }) => FileSystem.writeFileSync(file, content));
					})))
	});

const cmd = (command, opts) =>
	new Promise((resolve, reject) =>
		exec(command, opts = {}, (err, stdout) => err ? reject(err) : resolve(stdout)));

const readDirRecursively = (dir, predicate) => {
	let results = [];
	const directories = FileSystem.readdirSync(dir);

	directories.forEach((file) => {
		let path;

		if (dir.charAt(dir.length - 1) === '/') {
			path = dir + file;
		} else {
			path = dir + '/' + file;
		}

		const stat = FileSystem.statSync(path);

		if (stat && stat.isDirectory() && !exclusions(file)) {
			results = results.concat(readDirRecursively(path, predicate));
		} else if (predicate(path)) {
			results.push(path);
		}
	});

	return results;
};

const exclusions = (file) =>
	['build', '.settings', 'bin', '.idea', '.classpath', '.gitignore', '.project'].indexOf(file) >= 0;

const filters = (file) =>
	file.indexOf('.java') >= 0 ||
	file.indexOf('.kotlin') >= 0 ||
	file.indexOf('.scala') >= 0 ||
	file.indexOf('.feature') >= 0 ||
	file.indexOf('.gradle') >= 0 ||
	file.indexOf('.xml') >= 0;
