package jp.co.sysystem.training.guide.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveVersionRequest {
  
  @NotNull(message = "{validate.notnull}")
  private int fileNo;
  
  @NotBlank(message = "{validate.notblank}")
  private String fileId;
  
  @NotBlank(message = "{validate.notblank}")
  private String fileName;

  @NotBlank(message = "{validate.notblank}")
  private String task;

  @NotBlank(message = "{validate.notblank}")
  private int sortOrder;

  @NotBlank(message = "{validate.notblank}")
  private String author;
  private String content;
  private String commitMessage;
}
