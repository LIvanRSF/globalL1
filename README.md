# GitHub Java automated tests

<a name="up"></a>

## Content :bookmark_tabs:
* <a href="#stack">Technology stack</a>
* <a href="#objects">Testing subjects</a>
* <a href="#SystemProperty">Launch from Windows terminal</a>
* <a href="#Jenkins">Jenkins build</a>
* <a href="#AllureReport">Allure Report integration</a>
* <a href="#selenoid">Selenoid test run example video</a>
* <a href="#Telegram">Telegram notifications with Alert bot</a>



<a id="stack"></a>
## Technology stack :hammer_and_wrench:

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/technologies/intelij_idea.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/technologies/java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/technologies/junit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/technologies/selenide.svg" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="Rest Assured" height="50" src="images/technologies/rest_assured.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/technologies/selenoid.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/technologies/gradle.svg" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/technologies/jenkins.svg" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/technologies/allure_testops.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="images/technologies/allure.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/technologies/github.svg" width="50"/></a>
</div>



<a id="objects"></a>
## Testing subjects :mag:

Created automated tests with following checks:

### --== API. Tests of api.github.com ==--

:white_check_mark: create new user repository (test with authorization token)

:white_check_mark: delete new user repository (test with authorization token)

:white_check_mark: check user profile data. Parameterized with data from csv file

:white_check_mark: check user profile data. Parameterized with data from Stream

:white_check_mark: check user profile data. Parameterized with data from secret file

### --== UI. Tests of github.com ==--

:white_check_mark: edit user profile

:white_check_mark: check user registration date



<a id="SystemProperty"></a>
<h1 align="left">
<img src="images/technologies/terminale.png" width="25" height="25" alt="Jenkins"/>  <a name="Jenkins"><i>Launch from Windows terminal</i></a>
</h1>

```bash
gradle clean 
-DtestType=${TEST_TYPE}
-Dbrowser=${BROWSER}
-DbrowserSize=${BROWSER_SIZE}
-Dhost=${HOST}

```
> `${TEST_TYPE}` - type of test [ *test* <sub>(default)</sub> , *api_tests*, *combo_tests*, *ui_tests* ]
>
>
> `${BROWSER}` - browser name [ *chrome* <sub>(default)</sub> , *firefox*, *opera*]
>
> `${BROWSER_SIZE}` - browser window size  [ *1920x1080* <sub>(default)</sub> , *1366x768*, *1280x1024*]
>
> `${HOST}` - test run host [ *remote* <sub>(default)</sub> , *local* ]


<a id="Jenkins"></a>
<h1 align="left">
<img src="images/technologies/jenkins.svg" width="25" height="25" alt="Jenkins"/>  <a name="Jenkins"><i>Jenkins build</i></a>
</h1>

<a target="_blank" href="https://jenkins.autotests.cloud/job/Global_Diploma/">**Jenkins build link**</a>
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_API_tests"><img src="images/screenshots/JenkinsBuildFull.png" alt="Jenkins"/></a>  
</p>

<h1 align="left">
<img src="images/technologies/allure.svg" width="25" height="25" alt="Allure_Report"/>  <a name="AllureReport"><i>Allure Report integration</i></a>
</h1>

<a target="_blank" href="https://jenkins.autotests.cloud/job/Global_Diploma/8/allure/">**Allure report link**</a>
<p align="center">  


### *Main page*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screenshots/AllureMain.png">  
</p>  

### *Test cases*

<p align="center">  
<img title="Allure Tests" src="images/screenshots/AllureReportTests.png">  
</p>

### *Graphs*

  <p align="center">  
<img title="Allure Graphics" src="images/screenshots/AllureReportGraphs.png">  
</p>

<a id="selenoid"></a>
### <img alt="Selenoid" height="50" src="images/technologies/selenoid.svg" width="50"/>Selenoid</a>

### *Selenoid test run example video*

<p align="center"> 
<img title="Browserstack Video" src="images/video/EditGitHubProfile.gif" width="550" height="350"  alt="video">   
</p>

<a id="Telegram"></a>
<h1 align="left">
<img src="images/technologies/telegram.svg" width="25" height="25"  alt="Allure"/> <a name="Telegram"><i>Telegram notifications with Alert bot</i></a>
</h1>

<p align="center">  
<img title="Telegram notifications" src="images/screenshots/TelegrammNotification.png">  
</p>

[Up ⬆](#up)



