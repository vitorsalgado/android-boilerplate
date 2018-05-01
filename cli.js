'use strict'

const Inquirer = require('inquirer')
const FileSystem = require('fs')
const ChildProcess = require('child_process')

const exec = ChildProcess.execSync

Inquirer.prompt(
  [
    {
      type: 'input',
      name: 'project',
      message: 'What\'s the project name?',
      validate: input => !!input
    },
    {
      type: 'input',
      name: 'package',
      message: 'What\'s the project package name?',
      validate: input => !!input && input.indexOf('.') >= 0 && input !== 'com.example'
    }
  ])
  .then(answers => {
    const project = answers['project']
    const pkg = answers['package']
    const folders = pkg.replace(/\./g, '/')

    let pwd = exec('pwd')
    pwd = pwd.toString().replace('\n', '')

    const appBuildPath = `${pwd}/app/app.gradle`
    const appBuild = FileSystem.readFileSync(appBuildPath).toString()
    const changedAppBuild = appBuild.replace(/final app_name = 'Boilerplate'/, `final app_name = '${project}'`)

    const makefilePath = `${pwd}/Makefile`
    const makefile = FileSystem.readFileSync(makefilePath).toString()
    const changedMakefile = makefile.split('com.example').join(pkg)

    FileSystem.writeFileSync(appBuildPath, changedAppBuild)
    FileSystem.writeFileSync(makefilePath, changedMakefile)

    const root = `${pwd}`
    const libraries = [
      `${root}/app`,
      `${root}/resources`,
      `${root}/libs/analytics`,
      `${root}/libs/toolkit-android`,
      `${root}/libs/api`,
      `${root}/libs/toolkit`,
      `${root}/features/feature-about`,
      `${root}/features/feature-auth`
    ]
    const languages = [
      'java',
      'kotlin',
      'scala',
      'groovy'
    ]

    libraries.forEach(library =>
      languages.forEach(language =>
        FileSystem
          .readdirSync(`${library}/src`)
          .forEach(src => {
            try {
              const exists = FileSystem.existsSync(`${library}/src/${src}/${language}`)

              if (!exists) {
                return
              }

              exec(`cd ${library}/src/${src}/kotlin && mkdir -p ${folders} || true`)
              exec(`mv ${library}/src/${src}/${language}/com/example/* ${library}/src/${src}/${language}/${folders}/ || true`)
              exec(`rm -rf ${library}/src/${src}/${language}/com || true`)
            } catch (ex) {
              if (ex.code !== 'ENOENT') {
                throw ex
              }

              return
            }

            readDirRecursively(`${library}/`, inclusions)
              .map(file => ({ content: FileSystem.readFileSync(file), file }))
              .map(({ content, file }) => ({ content: content.toString(), file }))
              .map(({ content, file }) => ({ content: content.split('com.example').join(pkg), file }))
              .forEach(({ content, file }) => FileSystem.writeFileSync(file, content))
          })))
  })

const readDirRecursively = (dir, predicate) => {
  let results = []
  const directories = FileSystem.readdirSync(dir)

  directories.forEach(file => {
    let path

    if (dir.charAt(dir.length - 1) === '/') {
      path = dir + file
    } else {
      path = dir + '/' + file
    }

    const stat = FileSystem.statSync(path)

    if (stat && stat.isDirectory() && !exclusions(file)) {
      results = results.concat(readDirRecursively(path, predicate))
    } else if (predicate(path)) {
      results.push(path)
    }
  })

  return results
}

const exclusions = file => ['build', '.settings', 'bin', '.idea', '.classpath', '.gitignore', '.project'].includes(file)

const inclusions = file =>
  file.indexOf('.java') >= 0 ||
  file.indexOf('.kotlin') >= 0 ||
  file.indexOf('.kt') >= 0 ||
  file.indexOf('.scala') >= 0 ||
  file.indexOf('.feature') >= 0 ||
  file.indexOf('.gradle') >= 0 ||
  file.indexOf('.xml') >= 0 ||
  file.indexOf('.pro') >= 0
