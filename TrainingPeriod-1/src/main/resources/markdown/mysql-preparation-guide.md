# MySQL 準備編

## データベース作成手順

### 手順1. MySQLログイン
MySQLにログインする。
>![pre-1](/images/mysql/section4/pre-1.jpg)

### 手順2. データベース作成
以下のコマンドでデータベースを作成：
```sql
CREATE DATABASE kadaidb CHARACTER SET sjis;
```
>![pre-2](/images/mysql/section4/pre-2.jpg)


> 注意点：
> - 半角スペース、セミコロン(;)の入力に注意
> - データベース名の命名規則：名前（苗字のみ）+ db
> - 例：清水の場合は「shimizudb」

例：
```sql
CREATE DATABASE shimizudb CHARACTER SET sjis;
```

作成確認：「Query OK」が表示される。

### 手順3. データベース選択
```sql
USE kadaidb;
```
> 注：作成した自身のデータベースを使用すること
>![pre-3](/images/mysql/section4/pre-3.jpg)


### 動作確認

#### 確認1. テーブル作成
以下のSQLを実行：
```sql
CREATE TABLE TESTTABLE1 (
    TESTNO INT(3) NOT NULL,
    NAME VARCHAR(20),
    KANA VARCHAR(20),
    PRIMARY KEY (TESTNO)
);
```
>![pre-4](/images/mysql/section4/pre-4.jpg)


#### 確認2. テーブル確認
```sql
SHOW TABLES;
```
>![pre-5](/images/mysql/section4/pre-5.jpg)


## 次のステップ
文字コードの設定編に進んでください。
