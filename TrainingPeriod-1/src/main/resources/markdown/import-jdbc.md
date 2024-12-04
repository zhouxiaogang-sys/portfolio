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

# JDBCドライバのインストールガイド

## 概要
JavaプログラムからMySQLデータベースにアクセスするための環境構築手順を説明します。

## 前提条件
- Javaの開発環境が整っていること
- MySQLがインストールされていること

## JDBCとは？
JDBCとは、Java Database Connectivity（ジャバ データベース コネクティビティ）の略で、JavaからRDBMS（リレーショナルデータベース）にアクセスするためのJava標準APIです。

## インストール手順

### 1. MySQLコネクターのダウンロード
- OneDriveから`mysql-connector-j-9.0.0.zip`をダウンロードします
- パス：全社_共通＞教育＞01_社内研修＞02_各種課題＞01_Java研修＞02_JavaB課題＞02_環境構築＞各種インストーラー

### 2. ファイルの解凍
- ダウンロードしたzipファイルを解凍します。

### 3. ファイルの確認
- 解凍後、JARファイルが正しく展開されていることを確認します
  >![confirm](/images/projectB/JDBC/zip-confirm.jpg)

### 4. プロジェクトへのファイル配置
- 展開したJARファイルを、対象プロジェクトの`lib`フォルダにコピーします
  >..¥プロジェクト名¥src¥main¥webapp¥WEB-INF¥lib

  >![move](/images/projectB/JDBC/location-change.jpg) 

### 5. ビルドパスの設定
- プロジェクトを右クリック
- 「ビルド・パス」を選択
- 「ビルド・パスの構成」をクリック
    >![build-path](/images/projectB/JDBC/build-path.jpg)

### 6. JARファイルの追加
- 「ライブラリー」タブを選択
- クラスパスの「JARの追加」をクリック
    >![add-jar](/images/projectB/JDBC/add-jar.jpg)
- 以下のパスからJARファイルを選択：
    >![choose-jar](/images/projectB/JDBC/add-jar-2.jpg)


### 7. 設定の確認
- 参照ライブラリーに表示されていることを確認
  >![confirm](/images/projectB/JDBC/add-confirm.jpg)

## 注意事項
- B課題のSection2に進む際は、ドライバの配置場所や使用方法を自身で調査することが推奨されています
- 環境構築の理解には時間がかかる場合がありますが、自己調査を通じた学習が重要です

## 補足
>このガイドは基本的な設定手順を示しています。実際の開発環境や要件に応じて、適宜調整が必要になる場合があります。