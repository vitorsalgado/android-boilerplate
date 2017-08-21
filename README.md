# Android Boilerplate  
[![Build Status](https://travis-ci.org/vitorsalgado/android-boilerplate.svg?branch=master)](https://travis-ci.org/vitorsalgado/android-boilerplate)
[![Build Status](https://www.bitrise.io/app/4378da5487d8cf4e/status.svg?token=-n9uMzyOntb1uztJ-SeTqA&branch=master)](https://www.bitrise.io/app/4378da5487d8cf4e)
[![Coverage Status](https://coveralls.io/repos/github/vitorsalgado/android-boilerplate/badge.svg?branch=master)](https://coveralls.io/github/vitorsalgado/android-boilerplate?branch=master)
[![Code Climate](https://codeclimate.com/github/vitorsalgado/android-boilerplate/badges/gpa.svg)](https://codeclimate.com/github/vitorsalgado/android-boilerplate)

Android boilerplate application.  
The goal is to have a base from where new projects can start with basic architecture, configurations and continuous integration already set.  
This project comes too with several tools to support development.  
Continue reading below for more details.

## Mock API
This project comes with a Mock API already set. 
This makes development easier as developers become more independent of backend services.  
Check [Mock API documentation](/tools/mock/README.md) for more details.

## Code Quality
This project integrates a combination of unit tests, automated tests and code analysis tools. 
All linters are located on folder `tools/linters`.  

This project has a basic structure for automated tests with Calabash and Cucumber, located on folder `automated/tests`.  
Check [Automated Tests documentation](/automated_tests/README.md) for more details.

## License
This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).
