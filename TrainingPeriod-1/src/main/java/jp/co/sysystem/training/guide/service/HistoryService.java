package jp.co.sysystem.training.guide.service;

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

@Service
public class HistoryService {

  @Autowired
  private FileHistoryRepository historyRepository;

  public FileHistory updateHistory(String fileId, String content, String commitMessage,
          String editor) {
    FileHistory history = new FileHistory();
    history.setFileId(fileId);
    history.setContent(content);
    history.setCommitMessage(commitMessage);
    history.setEditor(editor);
    history.setCreateTime(LocalDateTime.now());
    history.setVersion(generateVersion());

    return historyRepository.save(history);
  }

  /**
   * ファイルの履歴を取得するメソッド
   * @param filename ファイル名
   * @return 履歴のリスト
   */
  public List<FileHistory> getFileHistory(String fileId) {

    return historyRepository.findByFileIdOrderByCreateTimeDesc(fileId);
  }

  /**
   * 特定バージョンの内容を取得するメソッド
   * @param filename ファイル名
   * @param version バージョン
   * @return ファイルの内容
   */
  public Optional<String> getVersionContent(String fileId, String version) {

    return historyRepository.findByFileIdAndVersion(fileId, version)
            .map(FileHistory::getContent);
  }

  /**
   * 最新バージョンを取得するメソッド
   * @param filename ファイル名
   * @return 最新バージョンの履歴
   */
  public Optional<FileHistory> getLatestVersion(String fileId) {

    return historyRepository.findTopByFileIdOrderByCreateTimeDesc(fileId);
  }

  /**
   * 2つのテキストコンテンツの差分を比較し、変更点のリストを返します。
   * 各行を比較し、追加・削除・変更された内容を検出します。
   *
   * @param oldContent 比較元の古いコンテンツ
   * @param newContent 比較対象の新しいコンテンツ
   * @return 変更内容を説明する文字列のリスト。各要素は追加、削除、または変更の詳細を含みます。
   */
  public List<String> compareVersions(String oldContent, String newContent) {
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
  }

  /**
   * バージョン番号を生成するメソッド
   */
  private String generateVersion() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
  }

}
