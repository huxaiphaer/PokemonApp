[![Build Status](https://travis-ci.org/huxaiphaer/PokemonApp.svg?branch=develop)](https://travis-ci.org/huxaiphaer/PokemonApp)
[![codecov](https://codecov.io/gh/huxaiphaer/PokemonApp/branch/develop/graph/badge.svg)](https://codecov.io/gh/huxaiphaer/PokemonApp)
[![Maintainability](https://api.codeclimate.com/v1/badges/8889b65c53bccc8236fd/maintainability)](https://codeclimate.com/github/huxaiphaer/PokemonApp/maintainability)

# PokemonApp

PokemonApp is a mobile application which shows the list and the details of different pokemons and their abilities.

#### Functionalities of the Application. 
* Retrieving the list of pokemons.
* Viewing the details or abilities of each pokemon selected.

## Getting Started.

This is how you can setup your application on your local machine.

#### Installation.

 * Ensure that you are having Android studio, then clone the project with the following command :
 
```
https://github.com/huxaiphaer/PokemonApp.git
```
* After cloning the repository , open   Android studio and wait for it to load. This might take a while but you have to ensure that all the builds finish.

* After that click on the play button away from the Right-top corner of Android Studio to run the application.

#### Running Test Reports

This project uses **Jacoco** to run both Instrumented and unit tests , below is the command :

```aidl
./gradlew jacocoTestReport

```

#### Running Tests With Coverage

```aidl
 ./gradlew createDebugCoverageReport

```

To be able to view the coverage report, navigate to `app/build/reports/coverage/index.html` , and open the file with the browser to view the test coverage report of the project.


### Languages/Libraries/Architecture Used.

* Java.
* MVVM (Model View View-Model)
* Paging Library.
* Recyclerview Animations.
* Travis (CI)
* Codecov. (For the test coverage).
* Android Data & View Binding.
* TDD (Test Driven Development).
* Code Climate.
* Xml.

### Author.

* Lutaaya Idris.