package jp.co.sysystem.training.guide.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sysystem.training.guide.service.DeleteService;
import jp.co.sysystem.training.guide.util.MessageUtil;

@Controller
public class DeleteController {
  @Autowired
  private DeleteService deleteService;

  @Autowired
  private MessageUtil msgutil;

  @PostMapping("/management/delete/{fileId}")
  public ResponseEntity<?> deleteGuide(@PathVariable String fileId) {
    try {
      deleteService.logicDelete(fileId);
      return ResponseEntity.ok()
              .body(Map.of("message", msgutil.getMessage("delete.success")));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
              .body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("サーバーエラーが発生しました。");
    }
  }
}
