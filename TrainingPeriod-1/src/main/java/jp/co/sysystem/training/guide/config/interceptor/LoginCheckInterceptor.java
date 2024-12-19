package jp.co.sysystem.training.guide.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  /**
   * コントローラーの処理が実行される前に呼び出されるメソッド
   * ログイン状態のチェックを行い、未ログインの場合はログイン画面にリダイレクト
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @param handler 処理対象のハンドラー
   * @return true: 処理を継続、false: 処理を中断
   * @throws Exception 例外が発生した場合
   */
  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response,
      Object handler) throws Exception {
    // セッションからユーザーIDを取得
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");

    // 未ログインの場合、ログイン画面へリダイレクト
    if (username == null) {
      response.sendRedirect("/");
      return false;
    }

    return true;
  }

  /**
   * コントローラーの処理が完了した後、ビュー描画前に呼び出されるメソッド
   * ユーザー情報をModelAndViewに追加し、ビューで使用できるようにする
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @param handler 処理対象のハンドラー
   * @param modelAndView ModelAndViewオブジェクト
   * @throws Exception 例外が発生した場合
   */
  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) throws Exception {
    // ModelAndViewが存在する場合のみ処理を実行
    if (modelAndView != null) {
      // セッションからユーザー情報を取得
      HttpSession session = request.getSession();
      String username = (String) session.getAttribute("username");

      // ユーザーIDをModelに追加
      modelAndView.addObject("username", username);

      // ユーザー名が存在する場合、Modelに追加
      String userName = (String) session.getAttribute("userName");
      if (userName != null) {
        modelAndView.addObject("userName", userName);
      }
    }
  }

  /**
   * リクエストの処理が完全に完了した後に呼び出されるメソッド
   * 現在は特別な処理は実装されていない
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @param handler 処理対象のハンドラー
   * @param ex 例外オブジェクト（処理中に例外が発生した場合）
   */
  @Override
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {
    // 後処理が必要な場合はここに実装
  }
}