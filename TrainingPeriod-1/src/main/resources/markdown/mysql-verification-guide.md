# MySQL 確認編

## インストール確認手順

### 手順1. コマンドプロンプトの起動
以下のいずれかの方法でコマンドプロンプトを起動：

#### パターン① メニューから起動
1. 「スタート」をクリック
2. 「Windowsシステムツール」を選択
3. 「コマンドプロンプト」をクリック
    >![confirm-1](/images/mysql/section3/confirm-1.jpg)

#### パターン② ファイル名を指定して実行
1. Windowsキー + R キーを押す
2. 「cmd」と入力
    >![confirm-2](/images/mysql/section3/confirm-2.jpg)
3. 「OK」をクリック
    >![confirm-3](/images/mysql/section3/confirm-3.jpg)


### 手順2. Cドライブへの移動
```cmd
cd ¥;
```
  > ![confirm-4](/images/mysql/section3/confirm-4.jpg)

### 手順3. MySQLへのログイン
```cmd
mysql -u root -p
```
> 注：半角スペースも正確に入力すること
> ![confirm-5](/images/mysql/section3/confirm-5.jpg)

### 手順4. パスワード入力
インストール編で設定したパスワードを入力する。
> ![confirm-6](/images/mysql/section3/confirm-6.jpg)


#### 確認完了の判断
接続成功のメッセージが表示されれば、確認完了です。
> ![confirm-7](/images/mysql/section3/confirm-7.jpg)


## 次のステップ
準備編に進んでください。
