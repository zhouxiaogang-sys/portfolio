package jp.co.sysystem.training.guide.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  private String fileId;
  private String fileName;
  private String task;
  private int sortOrder;
  private String author;
}
