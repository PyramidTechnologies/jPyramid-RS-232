[![Maven](https://maven-badges.herokuapp.com/maven-central/com.pyramidacceptors/jPyramid-RS-232/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pyramidacceptors/jPyramid-RS-232) [![Build Status](https://travis-ci.org/PyramidTechnologies/jPyramid-RS-232.svg?branch=develop)](https://travis-ci.org/PyramidTechnologies/jPyramid-RS-232) [![Coverage Status](https://coveralls.io/repos/PyramidTechnologies/jPyramid-RS-232/badge.svg?branch=master)](https://coveralls.io/r/PyramidTechnologies/jPyramid-RS-232?branch=master)

Overview
--------

This library is for OEMs and software developers looking to quickly and easily integrate an RS-232 bill validator
into their system. Get up and running quickly without having to worry about the low-level bit twiddling.

## Java Pyramid Device API

* Cross-platform, RS-232 Bill Validator API
* Supports Escrow Mode
* Highly Configurable
* Very lightweight library (small jar and low memory consumption)

### Use with Gradle ###

    dependencies {
        compile 'com.pyramidacceptors:jPyramid-RS-232:1.1'
    }

### Dependencies if you prefer to not use Gradle

* [Apache Commons Collection 4](https://commons.apache.org/proper/commons-collections/)
* [jSSC 2.8.0](https://github.com/scream3r/java-simple-serial-connector)
* [slf4j w/ log4j](http://www.slf4j.org/docs.html)
  - Note that we have this project configured to use log4j. If you prefer another framework
    you should be able to swap it out as described in the docs.
    
### Or Pick Your Maven Toolset ###

[Maven Central](http://search.maven.org/#artifactdetails%7Ccom.pyramidacceptors%7CjPyramid-RS-232%7C1.1%7Cjar)

### License ###

[This library is available under GNU General Public Use, V2](http://www.gnu.org/licenses/gpl-2.0.html)

### Contribution guidelines ###

We warmly welcome pull requests, feature requests, and any other feedback you are willing to offer.
