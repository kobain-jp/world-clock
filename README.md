# worldclock

## setup

### clone project

`git clone git@github.com:kobain-jp/worldclock.git`

if you are not familer with command line and click [here](https://github.com/Null-PE/JavaPlayground/blob/master/doc/setup.md)

上記の[URI] に https://github.com/Null-PE/JavaPlayground.git を貼り付けて、以下に変更する
https://github.com/Null-PE/JavaPlayground.git -> git@github.com:kobain-jp/worldclock.git

### import to eclipse
File > import > Gradle > Existing Gradle project
select `build.gradle` and right click > refresh gradle project

### lunch program

open your workspace in eclipse
select `src/main/java/com.dojo.worldclock.WorldclockApplication.java` and right click
Run As > Java Application

open url http://localhost:8080/hello 
then 'Welcome to SpringBoot' will be showed 

if you use 8080, you could change port by editing following file.

`src/main/resources/application.properties`

ex:if you use 8081
`server.port=8081`






