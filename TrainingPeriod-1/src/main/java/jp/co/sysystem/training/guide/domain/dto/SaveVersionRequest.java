package jp.co.sysystem.training.guide.domain.dto;

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
  private String author;
}
