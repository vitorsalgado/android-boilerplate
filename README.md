# Android Boilerplate  
[![Build Status](https://travis-ci.org/vitorsalgado/android-boilerplate.svg?branch=master)](https://travis-ci.org/vitorsalgado/android-boilerplate)
[![Build Status](https://www.bitrise.io/app/4378da5487d8cf4e/status.svg?token=-n9uMzyOntb1uztJ-SeTqA&branch=master)](https://www.bitrise.io/app/4378da5487d8cf4e)
[![Coverage Status](https://coveralls.io/repos/github/vitorsalgado/android-boilerplate/badge.svg?branch=master)](https://coveralls.io/github/vitorsalgado/android-boilerplate?branch=master)
[![Code Climate](https://codeclimate.com/github/vitorsalgado/android-boilerplate/badges/gpa.svg)](https://codeclimate.com/github/vitorsalgado/android-boilerplate)

Android boilerplate application.  
The goal is to have a base from where new projects can start with basic architecture, configurations and continuous integration already set.  
This project contains several tools and different languages all working together to build a nice development environment.  
Continue reading below for more details.

## Mock API
This project comes with a Mock Server already set. The mock tool was built on top of was built on top of [WireMock](http://wiremock.org/) and 
the reloader that comes together uses *Node.js* to watch file changes and restart WireMock.
This makes development easier as developers become more independent of backend services.  
Check [Mock API documentation](/mock-toolkit/README.md) for more details.

## Quality Assurance
This project integrates a combination of unit tests, automated tests and code analysis tools. 
All analysis tools are located on folder `tools/linters`.  

### Calabash Automated Tests
This project has a basic structure for automated tests with Calabash and Cucumber, located on folder `automated/tests`.  
Check [Automated Tests documentation](/automated_tests/README.md) for more details.

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

## Build Systems
Both *Gradle* and *Buck* are configured in this project.

## License
This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).
