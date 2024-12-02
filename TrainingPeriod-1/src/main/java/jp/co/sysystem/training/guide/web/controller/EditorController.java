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

    @GetMapping("/edit")
    public String index(Model model) {
        List<String> files = editorService.listFiles();
        model.addAttribute("files", files);
        return "index";
    }

    @GetMapping("/edit/{filename}")
    public String edit(@PathVariable String filename, Model model) {
        String content = editorService.readFile(filename);
        model.addAttribute("filename", filename);
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