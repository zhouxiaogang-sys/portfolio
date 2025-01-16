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

# 🚀 EclipseでのSpring Boot課題環境構築ガイド

## 📋 目次
- [EclipseでのSpring Boot環境構築ガイド](#-eclipseでのspring-boot環境構築ガイド)
- [前提条件](#prerequisites)
   - [必要なファイル](#required-files)
- [環境構築](#environment-setup)
   - [Pleiadesのインストール](#pleiades-installation)
   - [Java互換性設定](#java-compatibility)
   - [Checkstyle設定](#checkstyle-setup)
   - [フォーマッター設定](#formatter-setup)
   - [プラグイン設定](#plugin-setup)
  

<h2 id="prerequisites"> 前提条件 </h2>

<h3 id="required-files"> 必要なファイル</h3>

以下のファイルをご用意ください：
- [pleiades-2023](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EYPtiJ1x3U5MhJnm4PXdYo8Bm5cZsLPNfQm445TAzV-lSg?e=xNzgQJ)
- [仕様書](https://syshd01-my.sharepoint.com/:f:/g/personal/public_git_syshd01_onmicrosoft_com/Eg-qN3oFNh5Btdkruzxv7a8BDsSP-gZD0V_J6N7RRS2Mzg?e=tHtKd3)
- [プログラム雛形](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EdfplHAwTv9FiLeV5o6q7_ABoTXDyvJLE1YRjJtqCsEcVA?e=tukzc0)
- [sy-java-checkstyle.xml](https://syshd01-my.sharepoint.com/:u:/g/personal/public_git_syshd01_onmicrosoft_com/EYPbB4HBQlVPog6WA7-gYKEBacsoxRpM7iKSebMTAMtxAg?e=moMiVk)

<br>

<h2 id="environment-setup"> 環境構築 </h2>

<h3 id="pleiades-installation"> Pleiadesのインストール </h3>

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

<h3 id="java-compatibility">  Java互換性設定 </h3>

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

<h3 id="checkstyle-setup"> Checkstyle設定 </h3>

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

<h3 id="formatter-setup"> フォーマッター設定 </h3>

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

<h3 id="plugin-setup"> プラグイン設定 </h3>

1. 『ヘルプ』→『Eclipseマーケットプレイス』を開きます
    > ![market](../images/Spring/market.jpg)
2. 検索欄に「editorconfig」を入力
3. 『Go』ボタンを押下
4. ネズミマークのプラグインをインストールする
    > ![editorconfig](../images/Spring/editorconfig.jpg)

