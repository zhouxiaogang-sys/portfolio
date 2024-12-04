# MySQL 文字コード設定編

## 文字コード設定手順

### 手順1. データベース接続
MySQLにログインし、作成したデータベースに接続：
```sql
USE kadaidb;
```
>![charset-1](/images/mysql/section5/charset-1.jpg)
### 手順2. 現在の文字コード確認
```sql
status;
```
>![charset-2](/images/mysql/section5/charset-2.jpg)


### 手順3. 文字コードの変更
```sql
SET NAMES sjis;
```
>![charset-3](/images/mysql/section5/charset-3.jpg)

#### 変更後の確認
```sql
status;
```
>![charset-4](/images/mysql/section5/charset-4.jpg)


### データベース個別の文字コード変更
必要に応じて以下のコマンドを実行：
```sql
ALTER DATABASE kadaidb CHARACTER SET SJIS;
```
>![charset-5](/images/mysql/section5/charset-5.jpg)

## 次のステップ
time_zone設定編に進んでください。
