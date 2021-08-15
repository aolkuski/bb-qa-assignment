# TASK REPORT #

### TEST CASES ###

1) src/test/resources/manualtestcases/createArticleTestCases.txt
2) src/test/resources/manualtestcases/registerUserTestCases.txt

### REST API TESTS ###

1) com.backbase.bblog.restapitests.ArticleCreationTests.java
2) com.backbase.bblog.restapitests.RegisterUserTests.java

### UI TESTS ###

1) com.backbase.bblog.uitests.tests.ArticleCreationTests.java
    1) **userShouldBeAbleToCreateArticle() test should fail with the following
       message `Tag lists differs!`**
2) com.backbase.bblog.uitests.tests.RegistrationTests.java

### EVALUATION REPORT ###

#### BUGS ####

All the issues found during the task are listed below.

1) Found during exploratory testing

- Missing src attribute for an image in the 'card-footer' div, on a single article page
- Tags are not being added to the article
- Article's abstract/description in not shown on the Single Article page
- Comments are not persisted, when added from the Single Article page
- 'Likes' are not persisted in the database
- Article with too long title can't be created - exceeding 2048 signs causes violation of http
  length limit
- Internal application errors are presented to the user not in the meaningful way (example @above)

2) Found during writing scenarios

- [critical] Token after logout action is still working (logout via UI, token used via REST Api)
- Slug is not changed once the article's title is changed (should be changed according to the
  documentation)
- Once an article is deleted, moving back to previous page (for example profile) results in 404
  returned in a json. Only navigation to the main page helps.
- "Required fields" presence for article creation is not validated ('description' and 'body') can be
  missing

3) Found during automation

- Username is not preserving capital letters

#### WHY THOSE TESTS? ####

There are several reasons, why I chose these tests for automation:

1) These are the most often used functionalities, critical for the system. Having them automated
   gives dev team at least some confidence, that the application is working at minimum level.
2) These tests are quick and can be used both for regression and for sanity testing. They can also
   play crucial role for checking new commits, once they are plugged into CI/CD.
3) Tests are small as possible to give a quick feedback on what is broken (if something is)
4) To show at least few skills I have

#### FURTHER RECOMMENDATIONS ####

Definitely, I'd recommend automating as much as possible via REST Api tests, but first making sure
that UI uses only these endpoints. Those tests are faster, smaller and could reduce ui testing. I'd
also combine rest api and ui tests to create system tests - these would give much better picture of
the system. At the same time I would pay attention to test pyramid, to keep testing (in CI/CD) fast
and reliable. When it comes to risks, from business perspective, this app is far from being
user-friendly, so if it was to be released, many changes would have to be done. Hiring a UX person
would help a lot, though experienced QAs should be sufficient. From software perspective From
software perspective, I would care about Angular version and costs of moving to the newer release,
but that is a question for a developer of this app.

### AUTOMATIC REPORT ###

Report is being automatically generated after each run. If the following run is started with 'clean'
option, then the history of runs is erased, because the report is generated into 'target' directory.
Since the tool used for reporting is Allure, to view the report, 'mvn allure:report' command needs
to be run in the root directory of the project to get index.html file (located in '
target/site/index.html'). The other option is running 'mvn allure:serve', which will display a
report in the default browser (first setting up a temp jetty server).

### SUGGESTIONS FOR IMPROVEMENTS ###

App entry 'screen'

- app access behind more sophisticated authentication

User registration form

- mail verification: no mechanism to verify a mail address

New article creation form

- missing 'Cancel' button on new article creation
- missing info how to add tags to article (even grayed out)
- missing validation on fields length and content

Single article screen

- 'Edit Article' button at the bottom of the page is grayed out as if it was disabled
- @prev Edit/Delete Article section is duplicated - the one on the bottom should be removed
- Navigation to the previous page open 'Your Feed' regardless of the feed opened before
- 'Likes' count is not shown on the single article page
- Delete button doesn't trigger confirmation pop up

Feed page

- Pagination should be limited (for example to first, previous, next 3 pages and last)
- Popular Tags section should be limited to most popular tags and 'others'
- Empty tags should not be allowed
- When feed list is empty and resolution decreased (browser window is narrowed to <765px), tags'
  list take all available space

Navigation bar

- Missing Logout button/link (Profile Settings page is not suitable for that action)

Registration page

- Password requirements are not secure enough
- Inconsistent behavior: blank username -> sign up button active, but error on click. blank password
  -> sign up button inactive.

REST Api

- Missing endpoint for sign out
- Missing tags update endpoint or ability to update tags with article (or missing info about it in
  the documentation)

### HEADLESS MODE ###

To run tests in the headless mode (from console) use a system parameter  `-Dselenide.headless=true`,
when running tests. To run tests in the headless mode from IDE, go to the BaseUiTest and add a line
in the setup method (line 26): `Configuration.headless = true;`. Recompile tests and run them.

### ABOUT NOT OVERENGINEERING ###

Setup created in classes is not the best example, however, the quickest one. In a real-life case,
this should be moved to yaml file and managed by springboot. Also setting up all the code in the '
test' directory is an arguable thing, but it enables a quick plugging into the project with the
application. Frameworks I worked on so far, that were not in the same repo as the application, had
only tests in the 'test' directory. 
