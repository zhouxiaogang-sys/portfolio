package jp.co.sysystem.training.guide.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

@Service
public class LockService {
  @Autowired
  private GuidesRepository grep;
  
  public LocalDateTime getUpdateTime(String fileId) {
    
    MarkdownFile editObject = grep.findByFileId(fileId);
    LocalDateTime lockTime = editObject.getUpdateTime();
    
    return lockTime;
  } 
}
