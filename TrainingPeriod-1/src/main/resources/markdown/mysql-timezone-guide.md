# MySQL インストール手順

## time_zone設定編

### 手順1
OneDriveの以下のファイルより MySQLのtime_zoneのファイルをダウンロードします。

OneDriveのパス：全社_共通＞教育＞01_社内研修＞02_各種課題＞01_Java研修＞02_JavaB課題＞01_環境構築＞各種インストーラー

ファイル名：`timezone_2023c_posix_sql`
>![tz-1](/images/mysql/section6/timezone-1.jpg)

### 手順2
ダウンロードしたファイルを解凍する。
>![tz-2](/images/mysql/section6/timezone-2.jpg)


### 手順3
SQLファイルをエディタで開く。
改行コードが「LF」になっているため、「CRLF」に変更し、保存する。
※このようにすることで、コピー＆ペーストした際に、コマンドプロンプト上でSQL文が1行1行改行されて実行される。
>![tz-3](/images/mysql/section6/timezone-3.jpg)
>![tz-4](/images/mysql/section6/timezone-4.jpg)

### 手順4
コマンドプロンプトを開きMySQLにログインする。
>![tz-5](/images/mysql/section6/timezone-5.jpg)

### 手順5
以下のコマンドを入力し、データベースをMySQLに変換する。
```sql
USE mysql
```
>![tz-6](/images/mysql/section6/timezone-6.jpg)

※ここは自身のデータベースではないので注意！！！

### 手順6
エディタで開いたSQLをコピーし、コマンドプロンプトに貼り付ける。
しばらく時間がかかります。
以下の画面「COMMIT;」が表示されていることを確認し、MySQLをログアウトする。
>![tz-7](/images/mysql/section6/timezone-7.jpg)

### 手順7
Windows「サービス」画面を表示する。
「windowsキー 」+「R」キーを同時に押下し、「ファイル名を指定して実行」の画面を表示する。以下のコマンドを入力し、「OK」をクリックする。
```
services.msc
```
>![tz-8](/images/mysql/section6/timezone-8.jpg)
>![tz-9](/images/mysql/section6/timezone-9.jpg)

### 手順8
「MySQL80」のプロパティウィンドウを右クリック、「プロパティ」をクリックし、表示する。
>![tz-10](/images/mysql/section6/timezone-10.jpg)
>![tz-11](/images/mysql/section6/timezone-11.jpg)

### 手順9
「実行ファイルのパス」に記載のある以下のパスをコピーし、ファイルのあるフォルダを開く。
```
C:¥ProgramData¥MySQL¥MySQL Server 8.0¥my.ini
```
>![tz-12](/images/mysql/section6/timezone-12.jpg)

ファイルのパス：`C:¥ProgramData¥MySQL¥MySQL Server 8.0`
>![tz-13](/images/mysql/section6/timezone-13.jpg)

### 手順10
上記のファイルをコピーし、デスクトップに置き、エディタでファイルを開く。
>![tz-14](/images/mysql/section6/timezone-14.jpg)
>![tz-15](/images/mysql/section6/timezone-15.jpg)

### 手順11
「my.ini」ファイル内にタイムゾーン情報を追記をする。
以下の文字列を検索し、その文字列の下に追記、上書き保存をする。

検索文字列：`[mysqld]`
追記内容：`default-time-zone = 'Japan'`
>![tz-16](/images/mysql/section6/timezone-16.jpg)

### 手順12
修正したデスクトップの「my.ini」ファイルをMySQL配下「my.ini」のファイル（手順9のファイル）と置き換える。
デスクトップのファイルをコピーし、MySQL Server フォルダに貼り付ける。
>![tz-17](/images/mysql/section6/timezone-17.jpg)

### 手順13
Windowsのサーバー画面より、MySQLを停止する。
プロパティを開き「停止」をクリック、または、一覧より右クリック、「停止」をクリックする。
>![tz-18](/images/mysql/section6/timezone-18.jpg)

### 手順14
MySQLを開始する。（再起動）
>![tz-19](/images/mysql/section6/timezone-19.jpg)
>![tz-20](/images/mysql/section6/timezone-20.jpg)

### 手順15
コマンドプロンプトより、MySQLにログインし、以下のコマンドを入力しタイムゾーンの確認を行う。
```sql
show variables like '%time_zone%';
```
>![tz-21](/images/mysql/section6/timezone-21.jpg)


引き続き、B課題2-2を行う。