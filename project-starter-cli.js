'use strict'

const Inquirer = require('inquirer')
const FileSystem = require('fs')
const ChildProcess = require('child_process')

const exec = ChildProcess.execSync

const BASE_PACKAGE = 'br.com.vitorsalgado.example'
const BASE_PACKAGE_FOLDER = BASE_PACKAGE.split('.').join('/')
const BASE_APP_NAME = 'Boilerplate'

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
      validate: input => !!input && input.indexOf('.') >= 0 && input !== BASE_PACKAGE
    }
  ])
  .then(answers => {
    const project = answers['project']
    const pkg = answers['package']
    const folders = pkg.replace(/\./g, '/')

    let pwd = exec('pwd')
    pwd = pwd.toString().replace('\n', '')

    changeFile(`${pwd}/app/app.gradle`, x => x.replace(new RegExp("final app_name = '" + BASE_APP_NAME + "'"), `final app_name = '${project}'`))
    changeFile(`${pwd}/settings.gradle`, x => x.replace(new RegExp("rootProject.name = '" + BASE_APP_NAME + "'"), `rootProject.name = '${project}'`))
    changeFile(`${pwd}/fastlane/Appfile`, x => x.replace(new RegExp("package_name \"" + BASE_PACKAGE + "\""), `package_name "${pkg}"`))
    changeFile(`${pwd}/Makefile`, x => x.split(BASE_PACKAGE).join(pkg))

    const root = `${pwd}`

    const libraries = [
      `${root}/app`,
      `${root}/analytics`,
      `${root}/utils`,
      `${root}/api`
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
              exec(`mv ${library}/src/${src}/${language}/${BASE_PACKAGE_FOLDER}/* ${library}/src/${src}/${language}/${folders}/ || true`)
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
              .map(({ content, file }) => ({ content: content.split(BASE_PACKAGE).join(pkg), file }))
              .forEach(({ content, file }) => FileSystem.writeFileSync(file, content))
          })))

    exec(`rm -rf Dockerfile.cli`)
    exec(`rm -rf package.json`)
    exec(`rm -rf package-lock.json`)
    exec(`rm -rf .nvmrc`)
    exec(`rm -rf project-starter-cli.js`)
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

const changeFile = (path, transform) => {
  const content = FileSystem.readFileSync(path).toString()
  const changed = transform(content)

  FileSystem.writeFileSync(path, changed)
}

const exclusions = file => ['build', '.settings', 'bin', '.idea', '.classpath', '.gitignore', '.project'].includes(file)

const inclusions = file =>
  file.indexOf('.java') >= 0 ||
  file.indexOf('.kotlin') >= 0 ||
  file.indexOf('.kt') >= 0 ||
  file.indexOf('.kts') >= 0 ||
  file.indexOf('.scala') >= 0 ||
  file.indexOf('.feature') >= 0 ||
  file.indexOf('.gradle') >= 0 ||
  file.indexOf('.xml') >= 0 ||
  file.indexOf('.pro') >= 0
