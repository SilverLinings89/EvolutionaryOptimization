[![CircleCI](https://circleci.com/gh/SilverLinings89/EvolutionaryOptimization/tree/master.svg?style=svg)](https://circleci.com/gh/SilverLinings89/EvolutionaryOptimization/tree/master)
[![codecov](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization/branch/master/graph/badge.svg)](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization)
# EvolutionaryOptimization
This code serves as an example implenmentaion of the article I wrote on my [blog](https://light-and-code.com/?p=15)

## Classes

Mainly there are two relevant classes:
- The Expression class and the classes implementing it describe a mathematical expression such as x+2.
- The OptimizationRunner class which contains the main method and all the optimization logic and constants.

This is a fairly basic implementation and can serve as a starting point in case my article has peaked your interest.

## Usage

The main folder contains a script `gradlew` for Linux and `gradlew.bat` for Windows. This builds the code. Executing `gradlew test` runs the code.
After building the project, you can run the code by calling `java -classpath ./bin com.kraft.evolopti.OptimizationRunner` in the main directory. You can change the parameters in the OptimizationRunner class.

For convenience I recommend using Eclipse and importing the gradle project. This will enable you to run the code directly without manually building it each time. It also offers syntax highlighting, code completion and integrates well with gradle.

If eclipse shows an error in the tests about org.junit not being found, right click the project main folder, go to Build Path > Configure Build Path > Choose "Library" on the right > Add Library > Junit > Next and it should work.

## Coverage

![Coverage Graph](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization/branch/master/graphs/commits.svg)
