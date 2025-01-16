package jp.co.sysystem.training.guide.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

  //空白チェック用のアノテーション
  @NotBlank(message = "{validate.notblank}")
  private String username;

  @NotBlank(message = "{validate.notblank}")
  private String password;

  public static LoginDTO createNew(String username, String password) {
    LoginDTO dto = new LoginDTO();
    dto.setUsername(username);
    dto.setPassword(password);
    return dto;
  }

}