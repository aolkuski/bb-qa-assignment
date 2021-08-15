# BBLOG TEST FRAMEWORK

## Running tests

0. Prerequisites:
   1. latest java jdk installed
   2. latest mvn installed
   3. updated (latest) Chrome browser
   4. JAVA_HOME variable setup correctly
   5. Command line tool
1. Checkout the project
2. Open command line tool
3. Go to the project's root directory
4. Run one of the following commands:
    1. `mvn clean test` - which will run tests in the default browser
    2. `mvn -Dselenide.headless=true clean test` - which will run tests in the headless mode.

## Accessing the test report

Report files are being automatically generated after each run. If the following run is started
with 'clean' option, then the history of runs is erased, because the report is generated into '
target' directory. Since the tool used for reporting is Allure, to view the
report, `mvn allure:report` command needs to be run in the root directory of the project to get
index.html file (located in 'target/site/index.html'). The other option is
running `mvn allure:serve`, which will display a report in the default browser (first setting up a
temp jetty server). The server needs to be killed with Ctrl+C, in the console window.



