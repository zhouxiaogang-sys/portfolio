package jp.co.sysystem.training.guide.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import difflib.Delta;
import difflib.DiffUtils;
import jp.co.sysystem.training.guide.domain.repository.FileHistoryRepository;
import jp.co.sysystem.training.guide.domain.table.FileHistory;

@Service
public class HistoryService {

  @Autowired
  private FileHistoryRepository historyRepository;

  public FileHistory updateHistory(String fileId, String content, String commitMessage,
          String author) {
    FileHistory history = new FileHistory();
    history.setFileId(fileId);
    history.setContent(content);
    history.setCommitMessage(commitMessage);
    history.setAuthor(author);
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
    if (!fileId.endsWith(".md")) {
      fileId += ".md";
    }
    return historyRepository.findByFileIdOrderByCreateTimeDesc(fileId);
  }

  /**
   * 特定バージョンの内容を取得するメソッド
   * @param filename ファイル名
   * @param version バージョン
   * @return ファイルの内容
   */
  public Optional<String> getVersionContent(String fileId, String version) {
    if (!fileId.endsWith(".md")) {
      fileId += ".md";
    }
    return historyRepository.findByFileIdAndVersion(fileId, version)
            .map(FileHistory::getContent);
  }

  /**
   * 最新バージョンを取得するメソッド
   * @param filename ファイル名
   * @return 最新バージョンの履歴
   */
  public Optional<FileHistory> getLatestVersion(String fileId) {
    if (!fileId.endsWith(".md")) {
      fileId += ".md";
    }
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
    return DiffUtils.diff(
            Arrays.asList(oldContent.split("\n")),
            Arrays.asList(newContent.split("\n"))).getDeltas().stream()
            .map(delta -> formatDelta(delta))
            .collect(Collectors.toList());
  }

  /**
   * 差分の内容を人間が読みやすい形式にフォーマットします。
   *
   * @param delta 処理対象の差分情報を含むDeltaオブジェクト
   * @return フォーマットされた差分の説明文字列
   *         - 追加の場合: "内容追加: [追加された行]"
   *         - 削除の場合: "内容削除: [削除された行]"
   *         - 変更の場合: "修正: 原文=[元の行], 現在=[変更後の行]"
   */
  private String formatDelta(Delta<String> delta) {
    switch (delta.getType()) {
    case INSERT:
      return String.format("内容追加: %s", delta.getRevised().getLines());
    case DELETE:
      return String.format("内容削除: %s", delta.getOriginal().getLines());
    case CHANGE:
      return String.format("修正: 原文=%s, 現在=%s",
              delta.getOriginal().getLines(),
              delta.getRevised().getLines());
    default:
      return "未知の編集";
    }
  }

  /**
   * バージョン番号を生成するメソッド
   */
  private String generateVersion() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
  }

}
