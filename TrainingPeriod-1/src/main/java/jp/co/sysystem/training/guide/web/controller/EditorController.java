package jp.co.sysystem.training.guide.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jp.co.sysystem.training.guide.web.request.SaveVersionRequest;
import jp.co.sysystem.training.guide.web.response.ErrorResponse;
import jp.co.sysystem.training.guide.domain.table.FileHistory;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.exception.OptimisticLockException;
import jp.co.sysystem.training.guide.service.EditorService;
import jp.co.sysystem.training.guide.service.HistoryService;
import jp.co.sysystem.training.guide.service.LockService;
import jp.co.sysystem.training.guide.service.SaveService;

/**
 * エディター機能のコントローラークラス
 * ファイルの編集と保存の機能を提供する
 */
@Controller
public class EditorController {

  //エディターサービスの注入
  @Autowired
  private EditorService editorService;

  @Autowired
  private SaveService saveService;

  @Autowired
  HttpSession session;

  // ファイル履歴サービスの注入
  @Autowired
  private HistoryService fileHistoryService;

  private LocalDateTime oldLockTime;

  @Autowired
  private LockService lockService;

  private static final String EDITOR_PAGE = "page/editor";

  /**
   * ファイル編集画面を表示する
   * @param fileId ファイルID
   * @param model ビューモデル
   * @return エディター画面のテンプレート名
   */
  @GetMapping("/edit/{fileId}")
  public String edit(@PathVariable String fileId, Model model) {
    oldLockTime = lockService.getUpdateTime(fileId);

    // ファイルIDにマークダウン拡張子を追加
    String fullFileId = fileId + ".md";

    // ファイルの内容を読み込む
    String content = editorService.readFile(fullFileId);

    // ファイル名を取得
    MarkdownFile editObject = editorService.findObjectByFileId(fileId);

    // モデルに必要な情報を追加
    model.addAttribute("editObject", editObject);
    model.addAttribute("content", content);
    return EDITOR_PAGE;
  }

  /**
   * ファイルの内容を保存し、履歴を更新する
   * @param request 保存リクエスト（ファイルID、内容、コミットメッセージ、作成者を含む）
   * @return 更新された履歴情報
   */
  @PostMapping("/save/content")
  public ResponseEntity<?> saveVersion(@RequestBody SaveVersionRequest request) {
    String username = (String) session.getAttribute("username");
    try {
      FileHistory history = executeUpdate(request, username);
      // 更新された履歴情報をJSONで返す
      return ResponseEntity.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(history);

    } catch (OptimisticLockException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .contentType(MediaType.APPLICATION_JSON)
              .body(new ErrorResponse("保存に失敗しました。"));
    }

  }

  @Transactional
  private FileHistory executeUpdate(SaveVersionRequest request, String username) {
    LocalDateTime currentUpdateTime = editorService.findObjectByNo(request.getFileNo())
            .getUpdateTime();

    if (!currentUpdateTime.equals(oldLockTime)) {
      throw new OptimisticLockException("ファイルは変更されました");
    }
    // ファイルのプロパティーを更新
    saveService.saveGuideBookProperty(
            request.getFileNo(),
            request.getFileId(),
            request.getFileName(),
            request.getTask(),
            request.getSortOrder(),
            request.getAuthor());

    // 履歴情報を更新
    FileHistory history = fileHistoryService.updateHistory(
            request.getFileId(),
            request.getContent(),
            request.getCommitMessage(),
            username);
    
    // ファイルの内容を更新
    saveService.saveContent(
            request.getFileId(),
            request.getContent());

    return history;
  }

}