package jp.co.sysystem.training.guide.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sysystem.training.guide.domain.table.FileHistory;
import jp.co.sysystem.training.guide.service.HistoryService;

@Controller
public class HistoryController {

  @Autowired
  private HistoryService fileHistoryService;

  @GetMapping("/history/{fileId}")
  public String showHistoryPage(@PathVariable String fileId, Model model) {
    List<FileHistory> history = fileHistoryService.getFileHistory(fileId);
    model.addAttribute("history", history);
    model.addAttribute("fileId", fileId);
    return "page/history";
  }
  
  @GetMapping("/list/{fileId}")
  @ResponseBody
  public ResponseEntity<List<FileHistory>> getHistory(@PathVariable String fileId) {
    List<FileHistory> history = fileHistoryService.getFileHistory(fileId);
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(history);
  }

  @GetMapping("/compare")
  public ResponseEntity<?> compareVersions(
          @RequestParam String oldVersion,
          @RequestParam String newVersion) {
    List<String> diff = fileHistoryService.compareVersions(oldVersion, newVersion);
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(diff);
  }

}
