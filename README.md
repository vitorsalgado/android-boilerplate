# Android Boilerplate  
[![Build Status](https://travis-ci.org/vitorsalgado/android-boilerplate.svg?branch=master)](https://travis-ci.org/vitorsalgado/android-boilerplate)
[![Code Climate](https://codeclimate.com/github/vitorsalgado/android-boilerplate/badges/gpa.svg)](http://hits.dwyl.io/vitorsalgado/android-boilerplate)
[![Min. API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=19) 

Android boilerplate application.  
The goal is to have a base from where new projects can start with basic architecture, configurations and continuous integration already set.  
This project contains several tools and different languages all working together to build a nice development environment.  
Continue reading below for more details.

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

* [Infer](http://fbinfer.com/): Facebook Static analysis tool.
```
make infer # Infer must be installed locally
make infer-docker # runs Infer with Docker (does not need to install Infer locally)
make infer-docker-ci # for ci
```

* [SpotBugs](https://github.com/spotbugs/spotbugs): Static analysis tool to look for bugs in Java code:
```
make check
```

* [PMD](https://pmd.github.io/): It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. See [this project's PMD ruleset](tools/linters/pmd-ruleset.xml).
``` 
make pmd
```

* [Checkstyle](http://checkstyle.sourceforge.net/): It ensures that the code style follows the guidelines. See our [checkstyle config file](tools/linters/checkstyle.xml).
```
make checkstyle
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
