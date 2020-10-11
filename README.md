# 30分〜1時間でworldclockを作ろう！！

日本、中国、インドの時間が表示されるアプリを作ります。
[完成イメージ](https://github.com/kobain-jp/worldclock/blob/main/img-resource/goal-image.png)

実装後にわかること
- :SpringBoot(Spring Web)で新しいURLに対応したWebページの追加の仕方　@GetMapping/@Controller　
- :ControllerからTymeleafへのマッピングする値の渡し方
- h1タグ、imgタグ tableタグが使える
- thymeleafでの静的ファイル（css/js/img）の追加の仕方
- cssを利用した画面の装飾
- main tag/footer tagの利用
- 簡単ないくつかのjsメソッド window.location.reload

## setup

### clone project

`git clone https://github.com/kobain-jp/worldclock.git`

the above is created by following settings of spring initializr
[Spring Initilizrから作る設定例](https://github.com/kobain-jp/worldclock/blob/main/img-resource/how%20to%20generate%20template%20via%20spring%20initializr.png)

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

## 1. HelloControllerを参考に新しいWEBページを作る

URL http://localhost:8080/world-clock

Controllerクラスを作成する
com.dojo.worldclock.WorldClockController.java

```
//@Controllerアノテーションをつけること
@Controller
public class WorldClockController {

	//このアノテーションをつけることで、このURLがGetリクエストで呼ばれた場合にこのメソッドが呼ばれる
	@GetMapping("/world-clock")
	public String index() {
		//src/main/resources/templates以下に配置した引数の文字列.htmlがHTML作成に利用される
		return "index";
	}
}
```
//src/main/resources/templates以下のhello.htmlをコピーして、
index.htmlを作成する。

h1タグ内の文字列を変えてみよう

```
<h1>World Clock</h1>
```

http://localhost:8080/world-clock
にアクセス

[h1タグとは](https://developer.mozilla.org/ja/docs/Web/HTML/Element/Heading_Elements)

## 2. Javaで取得した現在日時を表示してみよう
Javaで取得した値をhtmlファイルにマッピングする


WorldClockController.indexメソッドを修正

```
//引数を変更 Model modelに
public String index(Model model) {

        //keyと値
		model.addAttribute("jpDate",new Date());
		return "index";
	}
    
```


index.htmlメソッドを修正

```
<body>
	<h1>World Clock</h1>
	<div th:text="${jpDate}"></div>
</body>
```

http://localhost:8080/world-clock にアクセス

-> index.htmlにjavaで取得した値が埋め込まれ、responseとしてブラウザに返される

## 3. 日本の国旗画像を表示しよう

SpringBootでは静的ファイルは以下に配置されます。

src/main/resources/static

1.以下のフォルダを追加しよう

```
src/main/resources/static/img
```
2.img-resource/jp.pngをimgフォルダにコピペ

```
src/main/resources/static/img/jp.png
```

*img-resourceはspringbootと関係ないです。
課題のために私が追加したフォルダです。

3.index.htmlにタグを追加

```
<body>
	<h1>World Clock</h1>
	<img th:src="@{/img/jp.png}">　　
	<div th:text="${jpDate}"></div>
</body>
```
http://localhost:8080/world-clock にアクセス

@{/img/} が　src/main/resources/static/img　ここ

imgタグとは
```MDN img```で検索



### 4. 日本国旗が見えないので背景をピンクにして、要素を真ん中によせよう

index.htmlの以下を修正

```
<body style="background-color: pink;text-align: center;">
```

http://localhost:8080/world-clock にアクセス

ただ、あまりおすすめされないやり方なので、cssファイルをつかいましょう

以下フォルダを作成

```
src/main/resources/static/css
```

右クリック> new > other > CSS Fileからapp.cssを作成

app.cssに以下を記載

```
body {　
	background-color: pink; 
       text-align: center;　
}
```
	
background-color: pink;　背景をピンクに
text-align: center;　　　テキスト、画像の子要素を真ん中に


index.htmlに以下を追加

```
<link th:href="@{/css/app.css}" rel="stylesheet">
```

http://localhost:8080/world-clock にアクセス

### 5. SimpleDateFormat 時間の表示形式を 曜日　00:00　に変更しよう

https://techacademy.jp/magazine/18611

WorldClockControllerに以下を記載

```
public String index(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
		String dateStr = sdf.format(new Date());
		model.addAttribute("jpDate", dateStr);
		// src/main/resources以下に配置した引数の文字列.htmlがHTML作成に利用される
		return "index";
	}
```

http://localhost:8080/world-clock にアクセス

### 6. 各国の時刻を表示しよう

中国国旗、インド国旗を表示しよう

img-resource/cn.png in.pngをsrc/main/resources/static/imgフォルダにコピペ

index.htmlをにimgタグを追加

```
	<img th:src="@{/img/jp.png}">
	<div th:text="${jpDate}"></div>
	
	<img th:src="@{/img/in.png}">
	<img th:src="@{/img/cn.png}">

```

http://localhost:8080/world-clock にアクセス

各国の時間を表示しよう

index.htmlに各国の時間用の変数の追加
 ${istDate} ${cnDate}

```
	<img th:src="@{/img/jp.png}">
	<div th:text="${jpDate}"></div>
	
	<img th:src="@{/img/in.png}">
	<div th:text="${istDate}"></div>
	
	<img th:src="@{/img/cn.png}">
	<div th:text="${cnDate}"></div>

```

WorldClockControllerで値をセットしよう

SimpleDateFormatにtimeZoneをセットしよう
参考:https://qiita.com/niwasawa/items/7ac1ea4c05c15e4b46fc

```
public String index(Model model) {

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
		String dateStr = sdf.format(new Date());
		model.addAttribute("jpDate", dateStr);

		// timezone インド
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		model.addAttribute("istDate", sdf.format(new Date()));

		// timezone　中国
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		model.addAttribute("cstDate", sdf.format(new Date()));
		
		return "index";
	}
```

http://localhost:8080/world-clock にアクセス

ちょっと改善
- new Dateは1回に
- 実行環境によらないように日本の時間もセット

```
public String index(Model model) {

     	SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");

		// 毎回 new したやつ使うとずれるから、一つのインスタンスを使い回そう
		Date date = new Date();
		// 日本　実行環境のデフォルトになってしまうから、セット
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo")); 
		model.addAttribute("jpDate", sdf.format(date));
		
		// timezone インド
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		model.addAttribute("istDate", sdf.format(date));

		// timezone　中国
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		model.addAttribute("cstDate", sdf.format(date));
```

http://localhost:8080/world-clock にアクセス

### 7. tableタグを使って、横に並べよう

table タグってなに？　`table　タグ mdn`で検索
https://developer.mozilla.org/ja/docs/Web/HTML/Element/table
上記のサイトでHTML Demoをいじって、レイアウトを作ってみよう

```
<table>
    <tbody>
        <tr>
            <td>JPの画像</td>
            <td>CNの画像</td>
            <td>INの画像</td>
        </tr>
        <tr>
            <td>JPの時間</td>
            <td>CNの時間</td>
            <td>INの時間</td>
        </tr>
    </tbody>
</table>

```

index.htmlに適用してみよう

```

<table>
		<tbody>
			<tr>
				<td><img th:src="@{/img/jp.png}"></td>
				<td><img th:src="@{/img/cn.png}"></td>
				<td><img th:src="@{/img/in.png}"></td>
			</tr>
			<tr>
				<td><div th:text="${jpDate}"></div></td>
				<td><div th:text="${cstDate}"></div></td>
				<td><div th:text="${istDate}"></div></td>
			</tr>
		</tbody>
	</table>
</body>


```

http://localhost:8080/world-clock にアクセス

あれ、横にいってしまった。
中央寄せにしよう

app.css

```
table {
	display: flex;
	justify-content: center;
}

```


### 8. フッターを追加しよう

index.html

```
	<footer>
		<p>2020 WORLD CLOCK FROM DOJO</p>
	</footer>
	
```

http://localhost:8080/world-clock にアクセス

F12 DeveloperToolでCSSの試行錯誤をしよう
試行錯誤した結果をコピペしよう

```

footer{
    position: absolute;
    bottom: 0;
    text-align: right;
    width: 95%;
}

```

http://localhost:8080/world-clock にアクセス

footerタグを使ったついでにHTML5の一般的な構造にしよう

```
<header></header>
<main></main>
<footer></footer>

```

index.htmlを編集

```
<body>
	<header>
		<h1>world clock</h1>
	</header>
	<main>
		<table>
			<tbody>
				<tr>
					<td><img th:src="@{/img/jp.png}"></td>
					<td><img th:src="@{/img/cn.png}"></td>
					<td><img th:src="@{/img/in.png}"></td>
				</tr>
				<tr>
					<td><div th:text="${jpDate}"></div></td>
					<td><div th:text="${cstDate}"></div></td>
					<td><div th:text="${istDate}"></div></td>
				</tr>
			</tbody>
		</table>
	</main>
	<footer>
		<p>2020 WORLD CLOCK FROM DOJO</p>
	</footer>
</body>

```

https://heysho.com/html5/

### 9. 時間が更新されないので、jsで定期的に更新されるようにしよう


30秒に一回自動でリロードがかかるようにしましょう

※今回はjsのインクルードと触りのためにこうしていますが、
本当に実装したいときは別の方法になると思います。

1.jsファイルを追加しよう

以下フォルダを作成

```
src/main/resources/static/js
```

右クリック> new > other > javascript からapp.jsを作成

app.jsに以下を記載

```
alert('dojo');
```

index.htmlにjs読み込みタグを追記

```
<div th:text="${jpDate}"></div>

<script type="text/javascript" th:src="@{/js/app.js}"></script>
</body>
</html>
```

http://localhost:8080/world-clock にアクセス
ダイアログが表示

app.jsを以下で上書き

```
function reload(){
  window.location.reload();
}

setTimeout(reload,3000);
```

setTimeout

https://techacademy.jp/magazine/5541

即時実行関数にしてnamespaceの競合を防ごう

```

(function () {

function reload(){
  window.location.reload();
}

setTimeout(reload,3000);


}());


```


### 10. 多言語対応しよう WorldClock <--> せかいどけい

src/main/resourcesに以下の名前のファイルを作成するが、
先にmessages-resouceフォルダにサンプルを作っといたので、コピペ

messages.properties　　

messages_ja.properties　

messages_zh.properties  

messages.properties
デフォルト

```
header.title=world clock
footer.signature=2020 WORLD CLOCK FROM DOJO
```

messages_ja.properties
日本語

```
header.title=せかいとけい
footer.signature=2020 せかいとけい FROM DOJO
```

messages_zh.properties
中国後

```
header.title=世界时钟
footer.signature=2020 世界时钟 FROM DOJO
```

index.htmlを編集

```
	<header>
		<h1 th:text="#{header.title}"></h1>
	</header>
	
	
	<footer>
		<p th:text="#{footer.signature}"></p>
	</footer>
	
```

http://localhost:8080/world-clock にアクセス

ブラウザの言語を切り替えて確認しよう

https://support.google.com/chrome/answer/173424?co=GENIE.Platform%3DDesktop&hl=ja


### 11. OSSライブラリを使って手軽にかっこいいフォントにしよう

ブラウザに実装されている総称フォントファミリーのどれかを使ってみる
https://developer.mozilla.org/ja/docs/Web/CSS/font-family

app.cssに以下を追加

```
h1 {
	font-family: cursive;
}

```

http://localhost:8080/world-clock にアクセス

Webフォントをつかって、もっとフォントにこだわる

好きなフォントを探そう
https://googlefonts.github.io/japanese/

index.htmlのheadタグに以下を追加

```

<link href="https://fonts.googleapis.com/earlyaccess/hannari.css" rel="stylesheet">

```

app.cssにフォントファミリ名　`'Hannari'`を追加
総称フォントファミリはクオートなし、フォントファミリはクオートあり
※なくても動くけど、一般的には上記で記載

```
h1 {
	font-family: 'Hannari', cursive;
}

```


英語用フォントも以下から探してみよう

https://fonts.google.com/


index.htmlのheadタグに追加

```

<link href="https://fonts.googleapis.com/css2?family=Chilanka&display=swap" rel="stylesheet">

```

app.cssに` 'Chilanka'`を追加

英語フォント,日本語フォント,総称フォントの順番に記載

```

h1 {
	font-family: 'Chilanka','Hannari', cursive;
}

```

http://localhost:8080/world-clock にアクセス

ブラウザの言語を切り替えて確認しよう

https://support.google.com/chrome/answer/173424?co=GENIE.Platform%3DDesktop&hl=ja


### 12 時計のアイコンを追加して、marginを使って位置を調整しよう

https://developer.mozilla.org/en-US/docs/Web/CSS/margin

index.htmlを編集

```
<header>
		<img th:src="@{/img/clock.png}">
		<h1 th:text="#{header.title}"></h1>
</header>
```

アナログ時計の上にもっと余白をと以下みたいにやると、
国旗の上にまで余白ができてしまうので、

```

img {
		margin-top: 10px;
}

```

clsssで指定するやり方でやろう

index.html

```

	<img class="worldclock-logo" th:src="@{/img/clock.png}">

```

app.css

```

.worldclock-logo {
		margin-top: 10px;
}

```

http://localhost:8080/world-clock にアクセス

ちなみにhtmlタグを指定するより、
classで上記のように指定する方が一般的です。

時計アイコンとアプリのタイトルを近づけよう
デザインの４原則のひとつ「近接」を利用しよう

https://bulan.co/swings/design4principals/

h1のデフォルトのマージンを上書きする

```

h1 {
		font-family: 'Chilanka','Hannari', cursive;
		margin-top: 5px;
}

```

http://localhost:8080/world-clock にアクセス

### 14.キャッチアップした知識を応用して自分の好きにカスタマイズしよう
-  背景を好きな写真にしてみよう

static/img/xxx.jpgに配置

index.htmlに以下を追加
※URLを指定する必要があるため

```

<style th:inline="text">
body{
    background: url([[@{/img/xxx.jpg}]])
                no-repeat center center fixed;
}
</style>

```

文字の色も変えよう

```

<style th:inline="text">
body{
    background: url([[@{/img/xxx.jpg}]])
                no-repeat center;
    color:white;
}
</style>

```

https://techacademy.jp/magazine/8610
https://techacademy.jp/magazine/9799

-  フッターや時刻を好きなフォントや文字の大きさを変えよう
-  国を増やしてみよう
-  時刻のフォーマットを変えてみよう

### 13. Gradleタスクで実行可能jarを作って、jarでアプリを起動させよう

Window > Show view > Gradle Tasks 
worldclock > build > bootJar を選択し、右クリックし Run Gradle Tasks

以下にjarが作成される
worldclock/build/libs/worldclock-0.0.1-SNAPSHOT.jar

jarがあるディレクトリまで移動して、以下コマンドで起動

```

java -jar worldclock-0.0.1-SNAPSHOT.jar --server.port=8081

```
--server.port=8081を指定しない場合は8080

http://localhost:8081/world-clock にアクセス

### 13. Herokuを利用してWorldClockを世界に公開しよう

アカウントを作ろう
https://signup.heroku.com/jp


heroku CLI をインストールしよう
https://devcenter.heroku.com/articles/getting-started-with-java#set-up

コマンドプロンプトから以下を実施

ワールドクロックのディレクトリまで移動
`cd worldclock`

いままので成果をローカルgitにコミット
`git status`

`git add .`
*普段はちゃんとファイル指定してね。。

`git commit -m 'commit files'`



`heroku login`と入力しEnter

`heroku: Press any key to open up the browser to login or q to exit:` 

と表示されるので、Enterし、ブラウザが立ち上がるので、ログイン

worldclockのルートディレクトリで以下のコマンドを実行

`heroku create`

herokuのリモートにgitリポジトリが作られる
ローカルでもremote add heroku　作られたリポジトリのアドレスが行われている。
`git remote`で確認

`git push heroku main`
*いつもはoriginのところがherokuになっている
*mainは最近人権運動のためmasterから変わりました

完了すると `Verifying deploy... done.`と表示される

`heroku ps:scale web=1`で起動し、
`heroku open`にてブラウザにてアプリが確認できます。

30分アクセスがないと自動で停止するが、
停止するには
`heroku ps:scale web=0``

お疲れ様でした！！
さぁ自分のアプリを作ってみよう！！

