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

# 🚀 Spring Boot課題プロジェクト設定ガイド

## 📋 目次

- [プロジェクト設定](#project-setup)
   - [DB環境構築](#db-setup)
   - [プロジェクトのインポート](#project-import)   
   - [設定ファイルの更新](#config-file-update)
   - [プロジェクト更新](#project-update)
   - [アプリケーションの実行](#application-execution)


<h2 id="project-setup"> プロジェクト設定 </h2>

<h3 id="db-setup"> DB環境構築 </h3>

1. 「workout」データベースを作成してください

2. テーブル作成：

   - 以下のSQL文を実行することで、以下に示すテーブルを生成できます。
     必要に応じて、SQL文をコピーしてデータベースで実行してください。
   ```mysql
   CREATE DATABASE workout;

   use workout;

   CREATE TABLE USER(
   ID VARCHAR(5) PRIMARY KEY,
   PASS VARCHAR(8),
   NAME VARCHAR(40),
   KANA VARCHAR(40)
   );

   CREATE TABLE USERDETAIL(
   NO INT(5) PRIMARY KEY,
   ID VARCHAR(5),
   BIRTH DATE,
   CLUB VARCHAR(40)
   );
   ```

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

   - 以下のSQL文を実行することで、指定されたテーブルにデータを挿入できます。
     必要に応じて、SQL文をコピーしてデータベースで実行してください。

   ```mysql
   INSERT USER VALUES
	   ('admin', 'admin', '管理者', 'ｶﾝﾘｼｬ')
   ,	('1', '1', '１', 'ﾃｽﾄﾜﾝ')
   ,	('test2', 'test2', 'てす', 'ﾃｽﾄﾂｰ')
   ,	('test3', 'test3', 'てすと', 'ﾃｽﾄｽﾘｰ')
   ,	('test4', 'test4', 'てすとて', 'ﾃｽﾄﾌｫｰ')
   ,	('yasui', 'yasui', '安井安井安', 'ﾔｽｲ')
   ,	('igaki', 'igaki', '井垣井垣井垣', 'ｲｶﾞｷ')
   ,	('711', 'seven007', 'セブンセブンせ', 'ｾﾌﾞﾝ')
   ,	('seya', 'seya0008', '瀬谷瀬谷瀬谷瀬谷', 'ｾﾔ')
   ,	('39', 'thankyou', '有賀有賀有賀有賀有', 'ｱﾘｶﾞ')
   ,	('4test', '4test', 'てすとててすとててす', 'ﾌｫｰﾃｽﾄ')
   ,	('3test', '3test', 'てすとててすとててすと', 'ｽﾘｰﾃｽﾄ')
   ,	('2test', '2test', 'てすとてすてすとてすてす', 'ﾂｰﾃｽﾄ')
   ,	('1test', '1test', 'てすとてすてすとてすてすと', 'ﾜﾝﾃｽﾄ')
   ,	('guest', 'guest', '客アカウント客アカウント客ア', 'ｷｬｸｱｶｳﾝﾄ')
   ,	('sirai', 'sirai', '白井白井白井白井白井白井白井白', 'ｼﾗｲ')
   ,	('umino', 'umino', '海野海野海野海野海野海野海野海野', 'ｳﾐﾉ')
   ,	('ueda', 'ueda', '上田上田上田上田上田上田上田上田上田', 'ｳｴﾀﾞ')
   ,	('imai', 'allmax', '今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井今井', 'ｲﾏｲｲﾏｲｲﾏｲﾏｲﾏｲｲﾏｲｲﾏｲﾏｲﾏｲｲﾏｲｲﾏｲﾏｲﾏｲｲﾏｲｲﾏｲﾏ')
   ,	('ozawa', 'allofmax', '小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢小沢', 'ｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜｵｻﾞﾜ')
   ,	('tada', 'namemax', '多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田多田', 'ﾀﾀﾞ')
   ,	('max', 'kanamax', '菅', 'ｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶﾞｽｶｽｶ')
   ;

   INSERT USERDETAIL VALUES
	   (99999, 'admin', '1990-01-06', 'SY委員会')
   ,	(00001, '1', '2020-06-01', '個人資格取得委員会')
   ,	(00002, 'test2', '2020-06-02', '書棚管理委員会')
   ,	(00003, 'test3', '2020-06-03', '美化管理委員会')
   ,	(00004, 'test4', '2020-05-04', '防災委員会')
   ,	(00005, 'yasui', '1994-04-04', 'モラル委員会')
   ,	(00006, 'igaki', '1996-01-04', '広報委員会')
   ,	(00007, '711', '1993-06-04', 'グループウェア委員会')
   ,	(00008, 'seya', '1997-06-24', '査定改善委員会')
   ,	(00009, '39', '1994-12-04', '安全衛生委員会')
   ,	(00010, '4test', '1998-03-04', '実践研修委員会')
   ,	(00011, '3test', '1992-06-14', '日本語能力向上委員会')
   ,	(00012, '2test', '1993-07-14', '親睦会委員会')
   ,	(00013, '1test', '1995-05-05', '人材開発委員会')
   ,	(00014, 'guest', '1994-09-06', 'SYの歴史と文化編集委員会')
   ,	(00015, 'sirai', '1989-06-12', '情報セキュリティ委員会')
   ,	(00016, 'umino', '1995-04-07', '国際交流委員会')
   ,	(00017, 'ueda', '1991-09-23', '外国籍社員サポート委員会')
   ,	(00018, 'imai', '1997-02-28', '外国籍社員サポート委員会')
   ,	(00019, 'ozawa', '1998-01-04', '査定改善委員会')
   ,	(00020, 'tada', '2001-05-24', '日本語能力向上委員会')
   ,	(00021, 'max', '2000-06-16', '個人資格取得委員会')
   ;

   commit;

   ```

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

<h3 id="project-import"> プロジェクトのインポート </h3>

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

<h3 id="config-file-update">  設定ファイルの更新 </h3>

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

<h3 id="project-update"> プロジェクト更新 </h3>

1. 「ファイル」->「Maven」->「プロジェクトの更新」
   > ![mavenUpdate1](../images/Spring/mavenUpdate1.jpg)

2. 使用可能なMavenコードベースを確認、『OK』ボタンをクリック。
   > ![mavenUpdate2](../images/Spring/update2.jpg)

<h3 id="application-execution"> アプリケーションの実行 </h3>

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