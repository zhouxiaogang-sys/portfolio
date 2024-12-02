# MySQL 文字コード設定編

## 文字コード設定手順

### 手順1. データベース接続
MySQLにログインし、作成したデータベースに接続：
```sql
USE kadaidb;
```

### 手順2. 現在の文字コード確認
```sql
status;
```

### 手順3. 文字コードの変更
```sql
SET NAMES sjis;
```

#### 変更後の確認
```sql
status;
```

### データベース個別の文字コード変更
必要に応じて以下のコマンドを実行：
```sql
ALTER DATABASE kadaidb CHARACTER SET SJIS;
```

## 次のステップ
time_zone設定編に進んでください。
