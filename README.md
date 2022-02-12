# NTUC Income QA Tasks

NTUC Income QA tasks using Serenity BDD Screenplay pattern

## Tools Used

```bash
Automation tool : Serenity
Programming language : Java
Build Tool : Maven
Testing Framework : Cucumber BDD
Reports : Serenity
Logger : Slf4J
```

## Project Folder structure

```python
==============
src/main/java:
==============
Page Objects - Object Repository
Utility - UI helper and reusable method
Tasks - contains the action methods used for the home page
Stepdefinition - contains the glue code corresponding to the Home Page

===================
src/main/resources:
===================
driver – Contains the drivers for chrome and firefox for Windows/Mac OS
Log4j – Used for logging and logs will be created in target folder


==============
src/test/java:
==============
Feature: Contains cucumber feature file written in Gherkin language/keywords
Test Runner: contains runner classes helps in kick start the execution through Junit/TestNG

==================
src/test/resources:
==================
Serenity config – Contains url for different environments like Dev, INT, Staging, browser configurations

=====================
Test Execution Steps:
=====================
1. src/test/java -> acceptancetests -> CucumberTestSuite.java
Right click on the file and run as Junit Test

(OR)

2. Right click on the pom.xml and run as Maven verify

=========
Reports :
=========
1) HTML Reports - target\site\serenity -> index.html
2) HTML Reports - target\site\serenity -> serenity-summary.html
```
