# Android Boilerplate  
[![Build Status](https://travis-ci.org/vitorsalgado/android-boilerplate.svg?branch=master)](https://travis-ci.org/vitorsalgado/android-boilerplate)
[![Code Climate](https://codeclimate.com/github/vitorsalgado/android-boilerplate/badges/gpa.svg)](https://codeclimate.com/github/vitorsalgado/android-boilerplate)
[![Min. API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=19) 

Android boilerplate application.  
The goal is to have a base from where new projects can start with basic architecture, configurations and continuous integration already set.  
This project contains several tools and different languages all working together to build a nice development environment.  
Continue reading below for more details.

## API Mock
This project comes with a Mock Server already set. The mock tool was built on top of was built on top of [WireMock](http://wiremock.org/) and 
the reloader that comes together uses *Node.js* to watch file changes and restart WireMock.
This makes development easier as developers become more independent of backend services.  
Check [Mock API documentation](/mock-toolkit/README.md) for more details.

## Quality Assurance
This project integrates a combination of unit tests, automated tests and code analysis tools. 
All analysis tools are located on folder `tools/linters`.  

### Code Analysis tools 
The following code analysis tools are set up on this project:

* [PMD](https://pmd.github.io/): It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. See [this project's PMD ruleset](tools/linters/pmd-ruleset.xml).
``` 
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/): This tool uses static analysis to find bugs in Java code. Unlike PMD, it uses compiled Java bytecode instead of source code.
```
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/): It ensures that the code style follows the guidelines. See our [checkstyle config file](tools/linters/checkstyle.xml).
```
./gradlew checkstyle
```

## License
This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).
