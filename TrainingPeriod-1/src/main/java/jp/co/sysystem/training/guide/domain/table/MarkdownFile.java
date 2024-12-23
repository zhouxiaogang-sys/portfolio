package jp.co.sysystem.training.guide.domain.table;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "markdown_file")
public class MarkdownFile implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private int fileNo;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String fileId;
    
    private String fileName;
    private String task;
    private int sortOrder;
    private String author;
    private LocalDateTime uploadTime;
    private LocalDateTime updateTime;
    private Boolean isDeleted;
    
    @PreUpdate
    protected void onUpdate() {
      updateTime = LocalDateTime.now();
    }
}
