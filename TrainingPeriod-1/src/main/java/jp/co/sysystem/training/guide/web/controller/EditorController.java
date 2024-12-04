package jp.co.sysystem.training.guide.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jp.co.sysystem.training.guide.service.EditorService;

import java.util.List;

@Controller
public class EditorController {

  @Autowired
  private EditorService editorService;

  @GetMapping("/edit/{fileId}")
  public String edit(@PathVariable String fileId, Model model) {
    String fullFileId = fileId + ".md";
    String content = editorService.readFile(fullFileId);
    String fileName = editorService.findNameById(fileId);

    model.addAttribute("fileName", fileName);
    model.addAttribute("content", content);
    return "page/editor";
  }

  @PostMapping("/save")
  @ResponseBody
  public ResponseEntity<String> save(@RequestParam String filename,
          @RequestParam String content) {
    try {
      editorService.saveFile(filename, content);
      return ResponseEntity.ok("セーブ成功");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("セーブ失敗：" + e.getMessage());
    }
  }
}