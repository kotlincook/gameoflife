# Game of Life in Kotlin

Some restrictions respected in this project due to the coderetreat rules are as follows:

* no loops (!)
* only one dot per line
* no abbreviations (almost :)
* 10 files per package
* 50 lines per class (except the test class)
* 4 lines per method
* one level of indention per method
* at most two instance variables per class
* don't use the else keyword
* no getters/setters

Remarks: 
* This version is not restricted to a finite (array) area. 
* Furthermore, it uses functional collection elements instead of loops 
and does not need mutable types.

It is not surprising that the code has become very readable through these rules.


## Setup
Minimal setup with Kotlin and kotlintest to get you started

    git clone https://github.com/kotlincook/gameoflife.git
    cd gameoflife
    gradle build

Open the directory in Idea.

## Running Tests

To execute the tests run `gradle test` or run the tests from the IDE you are using (e.g. IntelliJ)

## Optimized for Usage of
- Kotlin 1.3
- Gradle 3.4
- IntelliJ Idea 2018