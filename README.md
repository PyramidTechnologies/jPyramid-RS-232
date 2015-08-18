[![Maven](https://maven-badges.herokuapp.com/maven-central/com.pyramidacceptors/jPyramid-RS-232/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pyramidacceptors/jPyramid-RS-232) [![Build Status](https://travis-ci.org/PyramidTechnologies/jPyramid-RS-232.svg?branch=develop)](https://travis-ci.org/PyramidTechnologies/jPyramid-RS-232) [![Coverage Status](https://coveralls.io/repos/PyramidTechnologies/jPyramid-RS-232/badge.svg?branch=develop)](https://coveralls.io/r/PyramidTechnologies/jPyramid-RS-232?branch=develop)

Overview
--------

This library is for OEMs and software developers looking to quickly and easily integrate an RS-232 bill validator
into their system. Get up and running quickly without having to worry about the low-level bit twiddling.

## Important
If youa re using an Apex 7000 or Spectra, please make sure that all of your dip switches are in the off position. The unit msust be in RS-232 mode to use this library. For more information about configuration, please see our [faq](http://pyramidacceptors.com/support/faq/).

## Java Pyramid Device API

* Cross-platform, RS-232 Bill Validator API
* Supports Escrow Mode
* Highly Configurable
* Very lightweight library (small jar and low memory consumption)

### Use with Gradle ###

    dependencies {
        compile 'com.pyramidacceptors:jPyramid-RS-232:1.+'
    }

### Dependencies if you prefer to not use Gradle

* [Apache Commons Collection 4](https://commons.apache.org/proper/commons-collections/)
* [jSSC 2.8.0](https://github.com/scream3r/java-simple-serial-connector)
* [slf4j](http://www.slf4j.org/docs.html)
  - We have provided a sample config file for log4j.
    
### Dependencies if you prefer to not use Gradle

* [Apache Commons Collection 4](https://commons.apache.org/proper/commons-collections/)
* [jSSC 2.8.0](https://github.com/scream3r/java-simple-serial-connector)
    
### Or Pick Your Maven Toolset ###

[Maven Central](http://search.maven.org/#artifactdetails%7Ccom.pyramidacceptors%7CjPyramid-RS-232%7C1.1%7Cjar)

### Troubleshooting

On Linux, we've heard of some folks having trouble using the auto-detect port and getPortList fuctions. For reason's we have yet to identify, this appears to be resolved by running you app from terminal with the -jar switch.

    java -jar your_app.jar

### License ###

[This library is available under GNU General Public Use, V2](http://www.gnu.org/licenses/gpl-2.0.html)

### Contribution guidelines ###

We warmly welcome pull requests, feature requests, and any other feedback you are willing to offer.
