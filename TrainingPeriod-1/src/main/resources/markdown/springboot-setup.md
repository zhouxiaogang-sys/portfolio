<style>  
  body {
    line-height: 1.8;
  }
  span.tips {
    color: red;
    font-size: 18px;
    font-weight: bold;
  }
  span.link {
    font-size: 18px;
  }
</style>

# 🚀 EclipseでのSpring Boot環境構築ガイド

## 📋 目次
- [🚀 EclipseでのSpring Boot環境構築ガイド](#-eclipseでのspring-boot環境構築ガイド)
- [💻 前提条件](#prerequisites)
   - [📥 必要なファイル](#required-files)
- [🛠 環境構築](#environment-setup)
   - [📦 Pleiadesのインストール](#pleiades-installation)
   - [⚙️ Java互換性設定](#java-compatibility)
   - [🔍 Checkstyle設定](#checkstyle-setup)
   - [📝 フォーマッター設定](#formatter-setup)
   - [🔌 プラグイン設定](#plugin-setup)
- [📂 プロジェクト設定](#project-setup)
   - [🗃 DB環境構築](#db-setup)
   - [📥 プロジェクトのインポート](#project-import)   
   - [⚙️ 設定ファイルの更新](#config-file-update)
   - [💡 プロジェクト更新](#project-update)
   - [🚀 アプリケーションの実行](#application-execution)
  

<h2 id="prerequisites"> 💻 前提条件 </h2>

<h3 id="required-files"> 📥 必要なファイル</h3>

以下のファイルをご用意ください：
- [pleiades-2023](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EYPtiJ1x3U5MhJnm4PXdYo8Bm5cZsLPNfQm445TAzV-lSg?e=xNzgQJ)
- [仕様書](https://syshd01-my.sharepoint.com/:f:/g/personal/public_git_syshd01_onmicrosoft_com/Eg-qN3oFNh5Btdkruzxv7a8BDsSP-gZD0V_J6N7RRS2Mzg?e=tHtKd3)
- [プログラム雛形](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EdfplHAwTv9FiLeV5o6q7_ABoTXDyvJLE1YRjJtqCsEcVA?e=tukzc0)
- [sy-java-checkstyle.xml](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EYPbB4HBQlVPog6WA7-gYKEBacsoxRpM7iKSebMTAMtxAg?e=moMiVk)

<br>

<h2 id="environment-setup"> 🛠 環境構築 </h2>

<h3 id="pleiades-installation"> 📦 Pleiadesのインストール </h3>

<span class="tips">

[※pleiadesがすでにインストールされた場合、CheckStyleに移動します](#checkstyle-setup)

</span>

1. `pleiades-2023-12-java-win-64bit-jre_20240218.exe`を実行します
2. インストール先を選択し、『解凍』ボタンをクリックします
   > ![install](../images/Spring/install1.jpg)

3. インストール完了後：
    - `eclipse.exe`をコピーします
    - デスクトップで右クリックし、『ショートカットの貼り付け』を選択します
    - `eclipse.exe -clean.cmd`を実行します
   > ![clean](../images/Spring/clean.jpg)
   > ![shortcut](../images/Spring/shortcut.jpg)

4. ワークスペースを選択し、『起動』をクリックします
   > ![workspace](../images/Spring/workspacechoice.jpg)

<br>

<h3 id="java-compatibility"> ⚙️ Java互換性設定 </h3>

1. 『ウインドウ』→『設定』を開きます
   > ![set](../images/Spring/set1.jpg)

2. JREの設定：
    - 『Java』→『インストール済のJRE』を選択
    - Java 11を選択
    - 『適用』をクリック
   > ![jre](../images/Spring/set-jre.jpg)

3. コンパイラーの設定：
    - 『Java』→『コンパイラー』を選択
    - Java 11を選択
    - 『適用』をクリック
   > ![compiler](../images/Spring/set-compiler.jpg)

<br>

<h3 id="checkstyle-setup"> 🔍 Checkstyle設定 </h3>

1. 『ウインドウ』→『設定』を開きます
2. 設定手順：
    - 左メニューから『Checkstyle』を選択
    - 『新規』をクリック
   > ![cs1](../images/Spring/checkstyle-1.jpg)
    - 名前に「Spring課題チェック構成」を入力
    - 『インポート』をクリック
    - `sy-java-checkstyle.xml`を選択
   > ![cs2](../images/Spring/checkstyle-2.jpg)
    - 『Spring課題チェック構成』を選択し、『デフォルトとして設定』をクリック
    - 『適用してとじる』をクリック
   > ![cs3](../images/Spring/checkstyle-3.jpg)

<br>

<h3 id="formatter-setup"> 📝 フォーマッター設定 </h3>

1. CSSファイル設定：
    - 『Web』→『CSSファイル』→『エディター』を選択
    - 「スペースを使用したインデント」を選択
    - 「インデント・サイズ」を`4`に設定
   > ![f-css](../images/Spring/formatter-css.jpg)

2. HTMLファイル設定：
- 『Web』→『HTMLファイル』→『エディター』を選択
- 「スペースを使用したインデント」を選択
- 「インデント・サイズ」を`4`に設定
> ![f-html](../images/Spring/formatter-html.jpg)

<br>

<h3 id="plugin-setup"> 🔌 プラグイン設定 </h3>
1. 『ヘルプ』→『Eclipseマーケットプレイス』を開きます
    > ![market](../image/market.jpg)
2. 検索欄に「editorconfig」を入力
3. 『Go』ボタンを押下
4. ネズミマークのプラグインをインストールする
    > ![editorconfig](../image/editorconfig.jpg)

<h2 id="project-setup"> 📂 プロジェクト設定 </h2>

<h3 id="db-setup"> 🗃 DB環境構築 </h3>

1. 「workout」データベースを作成してください

2. テーブル作成：

   <details>
   <summary>📋 ユーザーマスタ (USER)</summary>

   | SEQ | 列名     | 列定義名 | データ型    | サイズ | キー |
   |-----|--------|------|---------|-----|----|
   | 1   | ユーザーID | ID   | VARCHAR | 5   | PK |
   | 2   | パスワード  | PASS | VARCHAR | 8   |    |
   | 3   | 名前     | NAME | VARCHAR | 40  |    |
   | 4   | カナ     | KANA | VARCHAR | 40  |    |

   </details>

   <details>
   <summary>📋 ユーザーマスタ詳細 (USERDETAIL)</summary>

   | SEQ | 列名     | 列定義名  | データ型    | サイズ | キー |
   |-----|--------|-------|---------|-----|----|
   | 1   | 番号     | NO    | INT     | -   | PK |
   | 2   | ユーザーID | ID    | VARCHAR | 5   |    |
   | 3   | 生年月日   | BIRTH | DATE    | -   |    |
   | 4   | 委員会    | CLUB  | VARCHAR | 40  |    |

   </details>

3. 初期データ登録：

   <details>
   <summary>📊 USERテーブル</summary>

   | 列名   | 値     |
   |------|-------|
   | ID   | admin |
   | PASS | admin |
   | NAME | 管理者   |
   | KANA | ｶﾝﾘｼｬ |

   </details>

   <details>
   <summary>📊 USERDETAILテーブル</summary>

   | 列名    | 値          |
   |-------|------------|
   | NO    | 99999      |
   | ID    | admin      |
   | BIRTH | 2020-08-03 |
   | CLUB  | 管理委員会      |

</details>

<h3 id="project-import"> 📥 プロジェクトのインポート </h3>

1. `spring-workout.zip`を解凍し、指定した`workspace`に移動します
2. 『ファイル』→『インポート』を選択
   > ![import](../images/Spring/maven1.jpg)

3. プロジェクトのインポート手順：
   - 『Maven』→『既存Mavenプロジェクト』を選択
   - 『次へ』をクリック
   > ![import1](../images/Spring/import.jpg)
   - 『ルート・ディレクトリー』でプロジェクトフォルダを選択
   > ![import2](../images/Spring/import-2.jpg)
   > ![import3](../images/Spring/import-3.jpg)
   - `pom.xml`ファイルの表示を確認
   - 『完了』をクリック

   > ![import4](../images/Spring/import-4.jpg)

<h3 id="config-file-update"> ⚙️ 設定ファイルの更新 </h3>

1. ワークスペースから`pom.xml`をダブルクリック
   > ![pom](../images/Spring/pom-xml.jpg)

2. データベース接続情報を更新：

```xml
<properties>
    <!-- Project Properties -->
    <java.version>11</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <!-- Database Properties -->
    <db.driver.puer>com.mysql.cj.jdbc.Driver</db.driver.puer>
    <db.driver>net.sf.log4jdbc.DriverSpy</db.driver>
    <jdbc.log4dbc.prefix>jdbc:log4</jdbc.log4dbc.prefix>
    <!-- ここ！ -->
    <datasource.url>jdbc:mysql://localhost:3306/workout?useUnicode=true&amp;characterEncoding=utf8</datasource.url>
    <datasource.username>root</datasource.username>
    <datasource.password>root</datasource.password>
    <!-- highlight-end -->
    <log.dir>./log</log.dir>
    <log.roll.history>5</log.roll.history>
</properties>
```

<h3 id="project-update"> 💡 プロジェクト更新 </h3>

1. 「ファイル」->「Maven」->「プロジェクトの更新」
   > ![mavenUpdate1](../images/Spring/mavenUpdate1.jpg)

2. 使用可能なMavenコードベースを確認、『OK』ボタンをクリック。
   > ![maveUpdate2](../images/Spring/update2.jpg)

<h3 id="application-execution"> 🚀 アプリケーションの実行 </h3>

1. アプリケーションの起動：
    - 『起動構成』→『Spring Boot アプリケーション』→『spring-workout』を右クリック
    - 『実行』を選択
   > ![run](../images/Spring/run.jpg)

2. 実行成功の確認：
   コンソールに以下の画面が表示されます
   > ![success](../images/Spring/success.jpg)

3. ログイン画面表示：
   - ブラウザで`http://localhost:8080`を入力。
   - ログイン画面が表示されたら環境構築完了
   > ![loginPage](../images/Spring/showlogin.jpg)

# END