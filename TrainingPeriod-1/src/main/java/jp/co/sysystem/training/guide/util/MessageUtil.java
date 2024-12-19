package jp.co.sysystem.training.guide.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * メッセージリソースから指定されたメッセージを取得する。<br>
 * メッセージおよび、フィールド名をリソースファイルへ定義する必要がある。
 * <ul>
 * <li>message.properties</li>
 * メッセージを定義する
 * <li>form.yaml</li>
 * 各formのフィールド名に対して名称を設定する
 * </ul>
 * @version 1.0.0 : 2020/05/13 新規作成
 */
@Component
public class MessageUtil {

  /**
   * messages.properties参照用インスタンス
   */
  @Autowired
  private MessageSource messageSource;

  /**
   * Bean validationが作成した文字列に
   * フィールド名を埋め込む
   * @param err
   * @return エラーメッセージ文字列
   */
  public String getMessage(ObjectError err) {
    return messageSource.getMessage(err, Locale.getDefault());
  }

  /**
   * メッセージをリソースファイルから取得
   * @param key メッセージプロパティファイルに定義されているメッセージのキー
   * @param fields
   * @param args
   * @return 取得したメッセージ文字列
   */
  public String getMessage(String key, String[] fields, String[] args) {
    return getMessage(key, fields, args, null);
  }

  /**
   * メッセージをリソースファイルから取得
   * @param key メッセージプロパティファイルに定義されているメッセージのキー
   * @param fields メッセージ内の{0}、{1}などのフィールドプレースホルダーを置換するフィールド名の配列
   * @param args メッセージ内の追加パラメータを置換する引数の配列
   * @param defaultMessage キーが見つからない場合のデフォルトメッセージ
   * @return 取得したメッセージ文字列、キーが見つからない場合はdefaultMessage
   */
  public String getMessage(String key, String[] fields, String[] args, String defaultMessage) {
    Object[] msgArgs = null;
    //
    if (!ArrayUtils.isEmpty(fields)) {
      msgArgs = new Object[] {
          new DefaultMessageSourceResolvable(fields, null, null)
      };
    }

    if (!ArrayUtils.isEmpty(args)) {
      msgArgs = ArrayUtils.addAll(msgArgs, (Object[]) args);
    }
    return messageSource.getMessage(
        key,
        msgArgs,
        defaultMessage,
        Locale.getDefault());
  }

  /**
   * メッセージをリソースファイルから取得
   * @param key メッセージプロパティファイルに定義されているメッセージのキー
   * @return 取得したメッセージ文字列
   */
  public String getMessage(String key) {
    return getMessage(key, null, null, null);
  }

  /**
   * メッセージをリソースファイルから取得
   * @param key メッセージプロパティファイルに定義されているメッセージのキー
   * @param defaultMessage キーが見つからない場合のデフォルトメッセージ
   * @return 取得したメッセージ文字列、キーが見つからない場合はdefaultMessage
   */
  public String getMessage(String key, String defaultMessage) {
    return getMessage(key, null, null, defaultMessage);
  }

  /**
   * Bean validationが作成した文字列で
   * フィールド名を埋め込む
   * @param map 既存のerrorMessages
   * @param err (ErrorsまたはBindingResult)
   * @return フィールド名をキーとするエラーメッセージのマップ
   */
  public HashMap<String, List<String>> interpolateMessages(HashMap<String, List<String>> map, Errors err) {
    if (err.hasErrors()) {
      for (FieldError e : err.getFieldErrors()) {
        // フィールドにエラーが未設定の場合はListを初期化
        if (!map.containsKey(e.getField())) {
          map.put(e.getField(), new ArrayList<String>());
        }
        map.get(e.getField()).add(getMessage(e));
      }
    }
    return map;
  }

  /**
   * Bean validationが作成した文字列で
   * フィールド名を埋め込む
   * @param err (ErrorsまたはBindingResult)
   * @return フィールド名をキーとするエラーメッセージのマップ
   */
  public HashMap<String, List<String>> interpolateMessages(Errors err) {
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    return this.interpolateMessages(map, err);
  }

  /**
   * BindingResultへエラーメッセージを設定
   * @param br BindingResultインスタンス
   * @param elementId htmlの要素ID
   * @param messageId messages.propertiesに定義しているメッセージのID
   * @param resourceFieldId messages.propertiesに定義しているエラー対象の項目名ID
   */
  public void addBindingResultFieldError(
      BindingResult br, String elementId, String messageId, String resourceFieldId) {
    addBindingResultFieldError(br, elementId, messageId, resourceFieldId, null);
  }

  /**
   * BindingResultへエラーメッセージを設定
   * @param br BindingResultインスタンス
   * @param elementId htmlの要素ID
   * @param messageId messages.propertiesに定義しているメッセージのID
   * @param resourceFieldId messages.propertiesに定義しているエラー対象の項目名ID
   * @param messsageArgs messages.propertiesに関連しないメッセージ引数
   */
  public void addBindingResultFieldError(
      BindingResult br, String elementId,
      String messageId, String resourceFieldId, Object[] messsageArgs) {
    Object[] msgArgs = null;

    if (!StringUtils.isEmpty(resourceFieldId)) {
      msgArgs = new Object[] {
          new DefaultMessageSourceResolvable(new String[] { resourceFieldId }, null, null)
      };
    }

    if (!ArrayUtils.isEmpty(messsageArgs)) {
      msgArgs = ArrayUtils.addAll(msgArgs, messsageArgs);
    }
    br.rejectValue(
        elementId,
        messageId,
        msgArgs,
        null);
  }
}
