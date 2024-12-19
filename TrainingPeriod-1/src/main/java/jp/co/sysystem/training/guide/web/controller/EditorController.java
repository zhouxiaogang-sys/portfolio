package jp.co.sysystem.training.guide.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jp.co.sysystem.training.guide.domain.dto.SaveVersionRequest;
import jp.co.sysystem.training.guide.domain.table.FileHistory;
import jp.co.sysystem.training.guide.service.EditorService;
import jp.co.sysystem.training.guide.service.HistoryService;

@Controller
public class EditorController {

  @Autowired
  private EditorService editorService;

  @Autowired
  private HistoryService fileHistoryService;

  @GetMapping("/edit/{fileId}")
  public String edit(@PathVariable String fileId, Model model) {
    String fullFileId = fileId + ".md";
    String content = editorService.readFile(fullFileId);
    String fileName = editorService.findNameById(fileId);

    model.addAttribute("fileId", fileId);
    model.addAttribute("fileName", fileName);
    model.addAttribute("content", content);
    return "page/editor";
  }

  @PostMapping("/save")
  public ResponseEntity<?> saveVersion(@RequestBody SaveVersionRequest request) {
    FileHistory history = fileHistoryService.updateHistory(
            request.getFileId(),
            request.getContent(),
            request.getCommitMessage(),
            request.getAuthor());
    
    editorService.saveFile(
            request.getFileId(),
            request.getContent());
    
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(history);
  }
}