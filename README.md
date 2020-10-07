# worldclockを作ろう！！

日本、中国、インドの時間が表示されるアプリを作ります。
[完成イメージ](https://github.com/kobain-jp/worldclock/blob/main/img-resource/goal-image.png)

## setup

### clone project

`git clone https://github.com/kobain-jp/worldclock.git`

if you are not familer with command line and click [here](https://github.com/Null-PE/JavaPlayground/blob/master/doc/setup.md)

手順5. 上記の[URI] に https://github.com/Null-PE/JavaPlayground.git を貼り付けて、以下に変更する
https://github.com/Null-PE/JavaPlayground.git -> https://github.com/kobain-jp/worldclock.git

### import to eclipse
File > import > Gradle > Existing Gradle project
select `build.gradle` and right click > refresh gradle project

### lunch program

open your workspace in eclipse

select `src/main/java/com.dojo.worldclock.WorldclockApplication.java` and right click

Run As > Java Application

open url http://localhost:8080/hello by using your browser and 'Welcome to SpringBoot' will be showed 

*if you use 8080, you could change port by editing following file.

`src/main/resources/application.properties`

`server.port=8081`
*the above is example to use 8081





