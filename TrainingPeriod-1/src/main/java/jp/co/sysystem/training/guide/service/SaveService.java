package jp.co.sysystem.training.guide.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.training.guide.domain.repository.FileHistoryRepository;
import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.repository.UserRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SaveService {

  @Autowired
  protected FileHistoryRepository hrep;

  @Autowired
  protected GuidesRepository grep;

  @Autowired
  protected UserRepository urep;

  //mdファイルが保存されるディレクトリのパス
  private static final String MD_DIR = "src/main/resources/markdown/";

  /**
  * ガイドブックのプロパティを保存する
  * 
  * @param fileNo ファイル番号
  * @param fileId ファイルID
  * @param fileName ファイル名
  * @param task タスク
  * @param sortOrder ソート順
  * @param author 作成者
  * @throws IllegalArgumentException パラメータが不正な場合にスロー
  * @throws NotFoundException 指定されたファイルが見つからない場合にスロー
  */
  @Transactional
  public void saveGuideBookProperty(int fileNo, String fileId, String fileName, String task,
          int sortOrder,
          String author) {
    try {
      // 入力値チェック
      if (fileNo <= 0) {
        throw new IllegalArgumentException("ファイル番号は1以上を指定してください");
      }
      if (fileId == null || fileId.trim().isEmpty()) {
        throw new IllegalArgumentException("ファイルIDを指定してください");
      }
      if (fileName == null || fileName.trim().isEmpty()) {
        throw new IllegalArgumentException("ファイル名を指定してください");
      }
      if (sortOrder < 0) {
        throw new IllegalArgumentException("ソート順は0以上を指定してください");
      }

      // ファイルの存在チェック
      MarkdownFile guideBook = grep.findByFileNo(fileNo);
      if (guideBook == null) {
        throw new NotFoundException("番号: " + fileNo + " のファイルが見つかりません");
      }

      // プロパティの更新
      guideBook.setFileId(fileId);
      guideBook.setFileName(fileName);
      guideBook.setTask(task);
      guideBook.setSortOrder(sortOrder);
      guideBook.setAuthor(author);
      guideBook.setUpdateTime(LocalDateTime.now());

      // 保存
      grep.save(guideBook);

    } catch (Exception e) {
      // エラーログを記録
      log.error("ガイドブックの保存中にエラーが発生しました。fileNo: " + fileNo, e);
      throw e;
    }
  }

  /**
   * // ファイルを保存するメソッド
   * @param filename
   * @param content
   */
  @Transactional
  public void saveContent(String fileId, String content) {
    // ファイルの保存
    try {

      if (!fileId.endsWith(".md")) {
        fileId += ".md";
      }
      Path path = Paths.get(MD_DIR, fileId);
      Files.writeString(path, content);

    } catch (IOException e) {
      throw new RuntimeException("保存失敗", e);
    }
  }

}
