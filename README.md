[![Build Status](https://travis-ci.org/ITDSystems/alvex-common.svg?branch=master)](https://travis-ci.org/ITDSystems/alvex-common)

Alvex common files
==================

This repository contains code and configuration files that are common for all Alvex components.

Build
-----

The component may be packaged in two ways: *amp* and *jar*.
To build amp use `mvn clean package`, to build installable jar use `mvn -P make-jar clean package`.

**Note**: this project requires Maven 3.3.9 at least.
