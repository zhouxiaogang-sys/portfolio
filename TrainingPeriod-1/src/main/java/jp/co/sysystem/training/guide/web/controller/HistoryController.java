package jp.co.sysystem.training.guide.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sysystem.training.guide.domain.table.FileHistory;
import jp.co.sysystem.training.guide.service.HistoryService;
import jp.co.sysystem.training.guide.web.request.CompareRequest;

@Controller
public class HistoryController {

  @Autowired
  private HistoryService fileHistoryService;

  private static final String HISTORY_PAGE = "page/history";

  @GetMapping("/history/{fileId}")
  public String showHistoryPage(@PathVariable String fileId, Model model) {
    List<FileHistory> history = fileHistoryService.getFileHistory(fileId);

    model.addAttribute("history", history);
    model.addAttribute("fileId", fileId);

    return HISTORY_PAGE;
  }

  @GetMapping("/list/{fileId}")
  @ResponseBody
  public ResponseEntity<List<FileHistory>> getHistory(@PathVariable String fileId) {
    List<FileHistory> history = fileHistoryService.getFileHistory(fileId);
    
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(history);
  }

  @PostMapping("/compare")
  public ResponseEntity<?> compareVersions(@RequestBody CompareRequest request) {
    if (request.getOldVersion() == null || request.getNewVersion() == null) {
      return ResponseEntity.badRequest().body("oldVersion and newVersion are required");
    }

    List<String> diff = fileHistoryService.compareVersions(
            request.getOldVersion(),
            request.getNewVersion());
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(diff);
  }
}
