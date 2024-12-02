<style>  
  body {
    line-height: 1.9;
  }
  span.tips {
    color: red;
    font-size: 18px;
    font-weight: bold;
  }
</style>

# 🚀 Eclipse環境構築ガイド

## 📋 目次
  - [💻必要なファイル](#necessary-file)
  - [📦 Pleiadesのインストール](#pleiades-installation)
  - [⚙️ セットアップ](#setup)
    - [📀️ Java互換性設定](#java-compatibility)
    - [📝 フォーマッター設定](#formatter-setup)    
  

<h2 id="necessary-file"> 💻必要なファイル </h2>

- 以下のファイルをご用意ください。
  - [pleiades-2023](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EYPtiJ1x3U5MhJnm4PXdYo8Bm5cZsLPNfQm445TAzV-lSg?e=xNzgQJ)
<br>

<h2 id="pleiades-installation"> 📦 Pleiadesのインストール </h2>

1. `pleiades-2023.exe`を実行します
2. 作成先フォルダを選択して、『解凍』ボタンをクリックします
   > ![install](/images/install/install1.jpg)

3. インストール完了後：
    - `eclipse.exe`をコピーします
    - デスクトップで右クリックし、『ショートカットの貼り付け』を選択します
    - `eclipse.exe -clean.cmd`を実行します<br>
   > ![clean](/images/install/clean.jpg)
   > ![shortcut](/images/install/shortcut.jpg)

4. ワークスペースを選択して、『起動』をクリックします<br>
   > ![workspace](/images/install/workspacechoice.jpg)

<br>

<h2 id="setup"> ⚙️ セットアップ </h2>

<h3 id="java-compatibility"> 📀️ Java互換性設定 </h3>

1. 『ウインドウ』→『設定』を開きます<br>
   > ![set](/images/install/set1.jpg)

2. 文字コードの設定：
    - 『一般』->『ワークスペース』を選択
    - 『テキスト・ファイル・エンコード』の設定で`UTF-8`を選択<br>
   >![encode](/images/install/workspace-encode.jpg)

3. JREの設定：
    - 『Java』→『インストール済のJRE』を選択
    - `Java 11`を選択します
    - 『適用』をクリックします<br>
   > ![jre](/images/install/set-jre.jpg)

4. コンパイラーの設定：
    - 『Java』→『コンパイラー』を選択
    - `Java 11`を選択
    - 『適用』をクリックします<br>
   > ![compiler](/images/install/set-compiler.jpg)

<br>
<h3 id="formatter-setup"> 📝 フォーマッター設定 </h3>

1. CSSファイル設定：
    - 『Web』→『CSSファイル』→『エディター』を選択
    - 『スペースを使用したインデント』を選択
    - 『インデント・サイズ』を`4`に設定します
    - 『適用』をクリックします<br>
   > ![f-css](/images/install/formatter-css.jpg)

2. HTMLファイル設定：
    - 『Web』→『HTMLファイル』→『エディター』を選択
    - 『スペースを使用したインデント』を選択します
    - 『インデント・サイズ』を`4`に設定します
    - 『適用』をクリックします<br>
    > ![f-html](/images/install/formatter-html.jpg)

3. Javaフォーマッター設定
   - フィルターで`フォーマッター`を入力
   - 『Java』->『コード・スタイル』->『フォーマッター』を選択
   - 『編集』をクリックします<br>
   >![f-java](/images/install/formatter-java-1.jpg)
   - 『タブ・ポリシー』で『スペースのみ』を選択し、『インデント・サイズ』を`4`に設定
   - 『行折り返し』で『行の最大幅』を`100`に設定
   - 『折り返しされた行のデフォルト・インデント』と『配列初期化子のデフォルト・インデント』を`4`に設定
   - 『適用』->『OK』を選択します
   - 『適用して閉じる』をクリックします<br>
   >![f-java-2](/images/install/java-format2.jpg)

   >![f-java-3](/images/install/java-format3.jpg)

<span class="tips">
※ Ctrl + Shift + Fキーでコードを整形できます</span>
<br><br>
