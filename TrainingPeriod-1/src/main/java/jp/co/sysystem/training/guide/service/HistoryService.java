package jp.co.sysystem.training.guide.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import jp.co.sysystem.training.guide.domain.repository.FileHistoryRepository;
import jp.co.sysystem.training.guide.domain.table.FileHistory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HistoryService {

  @Autowired
  private FileHistoryRepository historyRepository;

  /**
   * ファイルの履歴を更新する
   * 
   * @param fileId ファイルID
   * @param content ファイルの内容
   * @param commitMessage コミットメッセージ
   * @param editor 編集者
   * @return 保存された履歴エントリ
   * @throws IllegalArgumentException パラメータが不正な場合
   */
  public FileHistory updateHistory(String fileId, String content, String commitMessage,
          String editor) {
    try {
      // 入力値チェック
      if (fileId == null || fileId.trim().isEmpty()) {
        throw new IllegalArgumentException("ファイルIDを指定してください");
      }
      if (content == null) {
        throw new IllegalArgumentException("コンテンツを指定してください");
      }
      if (editor == null || editor.trim().isEmpty()) {
        throw new IllegalArgumentException("編集者を指定してください");
      }

      FileHistory history = new FileHistory();
      history.setFileId(fileId);
      history.setContent(content);
      history.setCommitMessage(commitMessage);
      history.setEditor(editor);
      history.setCreateTime(LocalDateTime.now());
      history.setVersion(generateVersion(fileId));

      return historyRepository.save(history);
    } catch (Exception e) {
      log.error("履歴の更新中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }
  }

  /**
   * ファイルの履歴一覧を取得する
   * 
   * @param fileId ファイルID
   * @return 履歴のリスト
   * @throws NotFoundException ファイルが見つからない場合
   */
  public List<FileHistory> getFileHistory(String fileId) {
    try {
      return historyRepository.findByFileIdOrderByCreateTimeDesc(fileId);
    } catch (Exception e) {
      log.error("履歴の取得中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }
  }

  /**
   * 特定バージョンの内容を取得する
   * 
   * @param fileId ファイルID
   * @param version バージョン
   * @return ファイルの内容
   * @throws NotFoundException 指定されたバージョンが見つからない場合
   */
  public Optional<String> getVersionContent(String fileId, String version) {
    try {
      return historyRepository.findByFileIdAndVersion(fileId, version)
              .map(FileHistory::getContent);
    } catch (Exception e) {
      log.error("履歴の取得中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }
  }

  /**
   * 最新バージョンを取得する
   * 
   * @param fileId ファイルID
   * @return 最新バージョンの履歴
   * @throws NotFoundException ファイルが見つからない場合
   */
  public Optional<FileHistory> getLatestVersion(String fileId) {
    try {
      return historyRepository.findTopByFileIdOrderByCreateTimeDesc(fileId);
    } catch (Exception e) {
      log.error("履歴の取得中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }

  }

  /**
   * 2つのバージョン間の差分を比較する
   * 
   * @param oldContent 古いバージョンの内容
   * @param newContent 新しいバージョンの内容
   * @return 差分情報のリスト
   * @throws IllegalArgumentException パラメータが不正な場合
   */
  public List<String> compareVersions(String oldContent, String newContent) {
    try {
      List<String> oldLines = Arrays.asList(oldContent.split("\n"));
      List<String> newLines = Arrays.asList(newContent.split("\n"));

      // パッチを生成（コンテキスト行3行を含む）
      Patch<String> patch = DiffUtils.diff(oldLines, newLines);
      List<Delta<String>> deltas = patch.getDeltas();

      // 結果を格納するリスト
      List<String> diffResults = new ArrayList<>();

      int currentPosition = 0;

      for (Delta<String> delta : deltas) {
        // 変更箇所の前のコンテキスト行を追加
        int contextStart = Math.max(0, currentPosition);
        int deltaStart = delta.getOriginal().getPosition();

        // 前のコンテキスト行を追加（最大3行）
        for (int i = Math.max(contextStart, deltaStart - 3); i < deltaStart; i++) {
          diffResults.add(" " + oldLines.get(i));
        }

        // 差分の種類に応じて処理
        switch (delta.getType()) {
        case INSERT:
          // 追加された行
          for (String line : delta.getRevised().getLines()) {
            diffResults.add("+" + line);
          }
          break;

        case DELETE:
          // 削除された行
          for (String line : delta.getOriginal().getLines()) {
            diffResults.add("-" + line);
          }
          break;

        case CHANGE:
          // 変更された行（削除行と追加行の組み合わせとして表現）
          for (String line : delta.getOriginal().getLines()) {
            diffResults.add("-" + line);
          }
          for (String line : delta.getRevised().getLines()) {
            diffResults.add("+" + line);
          }
          break;
        }

        // 変更箇所の後のコンテキスト行を追加（最大3行）
        int contextEnd = Math.min(oldLines.size(),
                delta.getOriginal().getPosition() + delta.getOriginal().getLines().size() + 3);
        for (int i = delta.getOriginal().getPosition()
                + delta.getOriginal().getLines().size(); i < contextEnd; i++) {
          diffResults.add(" " + oldLines.get(i));
        }

        currentPosition = contextEnd;
      }

      return diffResults;
    } catch (Exception e) {
      log.error("バージョン比較中にエラーが発生しました", e);
      throw e;
    }
  }

  /**
   * YYYYMMDD.numberの形のバージョン号を生成するメソッド
   * @param fileId
   * @return
   */
  private String generateVersion(String fileId) {
    String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE); // YYYYMMDD

    String versionPrefix = today + ".";
    Optional<String> maxVersion = historyRepository.findMaxVersionByFileIdAndPrefix(fileId,
            versionPrefix);

    if (maxVersion.isEmpty()) {
      return versionPrefix + "1";
    }

    String currentVersion = maxVersion.get();
    int currentSequence = Integer
            .parseInt(currentVersion.substring(currentVersion.lastIndexOf(".") + 1));

    return versionPrefix + (currentSequence + 1);
  }

}
