package jp.co.sysystem.training.guide.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

  //空白チェック用のアノテーション
  @NotBlank(message = "{validate.notblank}")

  private String username;

  @NotBlank(message = "{validate.notblank}")
  private String password;

}