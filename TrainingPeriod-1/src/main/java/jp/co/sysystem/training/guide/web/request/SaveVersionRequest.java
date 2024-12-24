package jp.co.sysystem.training.guide.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveVersionRequest {
  private String fileId;
  private String content;
  private String commitMessage;
}
