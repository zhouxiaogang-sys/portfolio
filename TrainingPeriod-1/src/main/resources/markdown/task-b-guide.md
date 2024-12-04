<style>  
  body {
    line-height: 1.8;
  }
  span.tips {
    color: red;
    font-size: 18px;
    font-weight: bold;
  }

</style>

# プロジェクト作成 - B課題

## 📋 目次
- [プロジェクト作成 - B課題](#project-b-create)
    - [Webプロジェクト作成](#create-web-project)
    - [パッケージとクラス作成](#create-package)
    - [サーバーの追加と起動](#add-tomcat)

<h2 id="create-web-project"> Webプロジェクト作成</h2>

- 『ファイル』->『新規』->『動的Webプロジェクト』をクリックします
  >![create-project-b](/images/projectB/create-projectB.jpg)
- 「プロジェクト名」入力します
  >プロジェクト名：TestWeb
- 「ターゲット・ランタイム」を`Tomcat9(Java11)`に変更し、『完了』をクリックします
  >![create-project-b-2](/images/projectB/create-projectB-2.jpg)
- 作成したプロジェクトを確認します<br>
  >![confirm](/images/projectB/create-confirm.jpg)

<h2 id="create-package"> パッケージとクラス作成 </h2>

- 作成したプロジェクトを右クリック->『新規』->『クラス』をクリックします
  >![create-class](/images/projectB/new-class.jpg)
- 「パッケージ」、「名前」を入力し、『完了』をクリック
  >パッケージ：studyb<br>
  名前：Test

  >![create-class-2](/images/projectB/new-class-2.jpg)
- 作成した`Test.java`に以下のソースコードを入力します

  ```java
  package studyb;

  import java.io.IOException;
  import java.io.PrintWriter;

  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

  @WebServlet("/Test")
  public class Test extends HttpServlet {
  private static final long serialVersionUID = 1L;

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          response.setContentType("text/html; charset=UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<html>");
          out.println("<head>");
          out.println("<title>Hello Web</title>");
          out.println("</head>");
          out.println("<body>");
          out.println("<h1>Hello Web!</h1>");
          out.println("</body>");
          out.println("</html>");
      }
  }
  ```
<h2 id="add-tomcat"> サーバーの追加と起動 </h2>

- 『実行』ボタンをクリック
- 『Tomcat9_Java11』->『完了』をクリック
  >![run](/images/projectB/server-add.jpg)
- 「コンソール」でサーバーが起動したことを確認します
  >![log](/images/projectB/server-log.jpg)
- Webブラウザを開き、以下のURLを入力
  > http://localhost:8080/TestWeb/Test
- ブラウザで以下の画面が表示されます
  >![success-page](/images/projectB/run-result.jpg)

