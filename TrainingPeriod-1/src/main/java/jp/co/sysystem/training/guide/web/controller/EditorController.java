package jp.co.sysystem.training.guide.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jp.co.sysystem.training.guide.web.request.SaveVersionRequest;
import jp.co.sysystem.training.guide.domain.table.FileHistory;
import jp.co.sysystem.training.guide.service.EditorService;
import jp.co.sysystem.training.guide.service.HistoryService;

/**
 * エディター機能のコントローラークラス
 * ファイルの編集と保存の機能を提供する
 */
@Controller
public class EditorController {

  //エディターサービスの注入
  @Autowired
  private EditorService editorService;

  // ファイル履歴サービスの注入
  @Autowired
  private HistoryService fileHistoryService;

  /**
   * ファイル編集画面を表示する
   * @param fileId ファイルID
   * @param model ビューモデル
   * @return エディター画面のテンプレート名
   */
  @GetMapping("/edit/{fileId}")
  public String edit(@PathVariable String fileId, Model model) {
    // ファイルIDにマークダウン拡張子を追加
    String fullFileId = fileId + ".md";

    // ファイルの内容を読み込む
    String content = editorService.readFile(fullFileId);

    // ファイル名を取得
    String fileName = editorService.findNameById(fileId);

    // モデルに必要な情報を追加
    model.addAttribute("fileId", fileId);
    model.addAttribute("fileName", fileName);
    model.addAttribute("content", content);
    return "page/editor";
  }

  /**
   * ファイルの内容を保存し、履歴を更新する
   * @param request 保存リクエスト（ファイルID、内容、コミットメッセージ、作成者を含む）
   * @return 更新された履歴情報
   */
  @PostMapping("/save")
  public ResponseEntity<?> saveVersion(@RequestBody SaveVersionRequest request) {
    // 履歴情報を更新
    FileHistory history = fileHistoryService.updateHistory(
            request.getFileId(),
            request.getContent(),
            request.getCommitMessage(),
            request.getAuthor());

    // ファイルの内容を保存
    editorService.saveFile(
            request.getFileId(),
            request.getContent());

    // 更新された履歴情報をJSONで返す
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(history);
  }
}