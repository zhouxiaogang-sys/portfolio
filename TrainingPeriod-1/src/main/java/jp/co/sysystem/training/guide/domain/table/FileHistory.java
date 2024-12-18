package jp.co.sysystem.training.guide.domain.table;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "file_history")
public class FileHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String filePath;
  private String content;
  private String commitMessage;
  private String author;
  private LocalDateTime createTime;
  private String version;

}
