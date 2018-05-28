# Android Boilerplate  
[![Build Status](https://travis-ci.org/vitorsalgado/android-boilerplate.svg?branch=master)](https://travis-ci.org/vitorsalgado/android-boilerplate)
[![Code Climate](https://codeclimate.com/github/vitorsalgado/android-boilerplate/badges/gpa.svg)](http://hits.dwyl.io/vitorsalgado/android-boilerplate)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/364ca80bee68464f9c4291106b1959ef)](https://www.codacy.com/app/vitorsalgado/android-boilerplate?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vitorsalgado/android-boilerplate&amp;utm_campaign=Badge_Grade)
[![Min. API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21) 

Android boilerplate application.  
The goal is to have a base from where new projects can start with basic architecture, configurations and continuous integration already set.  
This project contains several tools and different languages all working together to build a nice development environment.  
Continue reading below for more details.

## How to start a new project
This project has a predefined name and package structure (__Boilerplate__ and __br.com.vitorsalgado.example__).  
To facilitate new projects starting from this repository, 
there's a CLI tool written in JavaScript that fully replaces this predefined structure to new one, chosen by you. 
To use it, install latest Node.js version and then:
```
npm install
node cli.js
```

## Mock Server
This project comes with a Mock Server already set. The mock tool was built on top of was built on top of [WireMock](http://wiremock.org/) and 
the reloader that comes together uses *Node.js* to watch file changes and restart WireMock.
This makes development easier as developers become more independent of backend services.  
Check the [mock server documentation](/mock-toolkit/README.md) for more details.

## Quality Assurance
This project integrates a combination of unit tests, automated tests and code analysis tools. 
Check the list below:  

### Static Analysis Tools 
The following static analysis tools are set up on this project:

* [Ktlint](https://github.com/shyiko/ktlint): Lint for Kotlin
```
./gradlew ktlint
```

* [Detekt](https://github.com/arturbosch/detekt): Static analysis tool for Kotlin
```
./gradlew detektCheck
```

* [SonarQube](https://www.sonarqube.org/): Continuous code inspection tool.
```
Required environment variables:
SONARQUBE_ORG=<YOUR_SONARQUBE_ORGANIZATION_KEY>
SONARQUBE_TOKEN=<YOUR_GENERATED_SONARQUBE_TOKEN>

make sonar
```

### Security Tools

* [OWASP Dependency Check](https://github.com/jeremylong/DependencyCheck): Detects publicly disclosed vulnerabilities in application dependencies.
```
make check-security
```

* [MobSF](https://github.com/MobSF/Mobile-Security-Framework-MobSF): Mobile Security Framework.
```
make mobsf
Navigate to: http://localhost:8000
```

* [Qark](https://github.com/linkedin/qark): Look for security vulnerabilities.
```
make qark # Python required
```

## License
This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).
