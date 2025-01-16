package jp.co.sysystem.training.guide.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideBookDTO {
  private int fileNo;
  private String fileId;
  private String fileName;
  private String task;
  private int sortOrder;
  private String author;
  private LocalDateTime uploadTime;
  private LocalDateTime updateTime;
  private Boolean isDeleted;

  public static GuideBookDTO createNew(String fileId, String fileName, String task,
          int sortOrder, String author) {
    GuideBookDTO dto = new GuideBookDTO();
    dto.setFileId(fileId);
    dto.setFileName(fileName);
    dto.setTask(task);
    dto.setSortOrder(sortOrder);
    dto.setAuthor(author);
    dto.setUploadTime(LocalDateTime.now());
    dto.setUpdateTime(LocalDateTime.now());
    dto.setIsDeleted(false);
    return dto;
  }

}
