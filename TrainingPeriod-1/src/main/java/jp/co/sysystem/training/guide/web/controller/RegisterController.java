package jp.co.sysystem.training.guide.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jp.co.sysystem.training.guide.service.RegisterService;
import jp.co.sysystem.training.guide.util.MessageUtil;
import jp.co.sysystem.training.guide.web.request.RegisterRequest;

@Controller
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  @Autowired
  private MessageUtil msgutil;

  @PostMapping("/management/register")
  public ResponseEntity<?> registerGuide(@RequestBody RegisterRequest request) {
    try {
      registerService.registerNewGuideBooks(
              request.getFileId(),
              request.getFileName(),
              request.getTask(),
              request.getSortOrder(),
              request.getAuthor());

      return ResponseEntity.ok()
              .body(Map.of("message", msgutil.getMessage("register.success")));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
              .body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("サーバーエラーが発生しました。");
    }
  }
}
