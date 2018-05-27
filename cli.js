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

    const settingsPath = `${pwd}/settings.gradle`
    const settings = FileSystem.readFileSync(settingsPath).toString()
    const changedSettings = settings.replace(/rootProject.name = 'Boilerplate'/, `rootProject.name = '${project}'`)

    const fastlaneAppFilePath = `${pwd}/fastlane/AppFile`
    const fastlaneAppFile = FileSystem.readFileSync(fastlaneAppFile).toString()
    const changedFastlaneAppFile = fastlaneAppFile.replace(/package_name \"com.example\"/, `package_name '${pkg}'`)

    const makefilePath = `${pwd}/Makefile`
    const makefile = FileSystem.readFileSync(makefilePath).toString()
    const changedMakefile = makefile.split('com.example').join(pkg)

    FileSystem.writeFileSync(appBuildPath, changedAppBuild)
    FileSystem.writeFileSync(settingsPath, changedSettings)
    FileSystem.writeFileSync(makefilePath, changedMakefile)
    FileSystem.writeFileSync(fastlaneAppFilePath, changedFastlaneAppFile)

    const root = `${pwd}`

    const libraries = [
      `${root}/app`,
      `${root}/buildSrc`,
      `${root}/resources`,
      `${root}/libs/analytics`,
      `${root}/libs/toolkit-android`,
      `${root}/libs/api`,
      `${root}/libs/toolkit`
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

    exec(`rm -rf Dockerfile.cli`)
    exec(`rm -rf package.json`)
    exec(`rm -rf package-lock.json`)
    exec(`rm -rf .nvmrc`)
    exec(`rm -rf cli.js`)
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
  file.indexOf('.kts') >= 0 ||
  file.indexOf('.scala') >= 0 ||
  file.indexOf('.feature') >= 0 ||
  file.indexOf('.gradle') >= 0 ||
  file.indexOf('.xml') >= 0 ||
  file.indexOf('.pro') >= 0
