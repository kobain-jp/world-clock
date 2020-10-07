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

```
public String index(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
		String dateStr = sdf.format(new Date());
		model.addAttribute("jpDate", dateStr);
		// src/main/resources以下に配置した引数の文字列.htmlがHTML作成に利用される
		
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		model.addAttribute("istDate", sdf.format(new Date()));
		
		return "index";
	}
```

### 7. 横に並べよう

tableタグの利用 

### 7. フッターを追加しよう

main、footerタグを利用したhtmlの構造化

### 8. 時間が更新されないので、jsで定期的に更新されるようにしよう


30秒に一回自動でリロードがかかるようにしましょう

※今回はjsのインクルードと触りのためにこうしていますが、
本当に実装したいときは別の方法になると思います。

1.jsファイルを追加しよう

以下フォルダを作成

```
src/main/resources/static/jsja
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
