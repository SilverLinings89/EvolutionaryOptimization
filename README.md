[![CircleCI](https://circleci.com/gh/SilverLinings89/EvolutionaryOptimization/tree/master.svg?style=svg)](https://circleci.com/gh/SilverLinings89/EvolutionaryOptimization/tree/master)
[![codecov](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization/branch/master/graph/badge.svg)](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization)
# EvolutionaryOptimization
This code serves as an example implenmentaion of the article I wrote on my [blog](http://pascal-kraft.com/index.php/blog/166-evolutionary-optimization)

## Classes

Mainly there are two relevant classes:
- The Expression class and the classes implementing it describe a mathematical expression such as x+2.
- The OptimizationRunner class which contains the main method and all the optimization logic and constants.

This is a fairly basic implementation and can serve as a starting point in case my article has peaked your interest.

## Usage

You should not need any further files. Simply compile and run. The commands `javac OptimizationRunner.java` and `java OptimizationRunner` should run the code (which is located in the folder `src/main/com/kraft/evolopti`. The tests are located in `src/test/com/kraft/evolopti`. The parameters can be set in OptimizationRunner.java.
This is also a gradle project which you can use if you know gradle. More introduction will follow later.

## Coverage

![Coverage Graph](https://codecov.io/gh/SilverLinings89/EvolutionaryOptimization/branch/master/graphs/commits.svg)