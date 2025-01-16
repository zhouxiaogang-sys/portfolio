package jp.co.sysystem.training.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.exception.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditorService {

  @Autowired
  private GuidesRepository grep;

  // mdファイルが保存されるディレクトリのパス
  private static final String MD_DIR = "src/main/resources/markdown/";

  public EditorService() {
    createMdDirectory();// 初期化時にディレクトリを作成
  }

  /**
   * ディレクトリを作成するメソッド
   */
  private void createMdDirectory() {
    try {
      Files.createDirectories(Paths.get(MD_DIR));
    } catch (IOException e) {
      throw new RuntimeException("ディレクトリ作成失敗", e);
    }
  }

  /**
   * ファイルIDによってMarkdownファイルオブジェクトを検索する
   * 
   * @param fileId ファイルID
   * @return 該当のMarkdownファイルオブジェクト
   * @throws IllegalArgumentException fileIdがnullまたは空の場合にスロー
   * @throws NotFoundException 該当ファイルが見つからない場合にスロー
   */
  public MarkdownFile findObjectByFileId(String fileId) {
    try {
      if (fileId == null || fileId.trim().isEmpty()) {
        throw new IllegalArgumentException("ファイルIDを指定してください");
      }
      MarkdownFile file = grep.findByFileId(fileId);
      if (file == null) {
        throw new NotFoundException("ID: " + fileId + "のファイルが見つかりません");
      }
      return file;
    } catch (Exception e) {
      throw e;
    }

  }

  /**
   * ファイル番号によってMarkdownファイルオブジェクトを検索する
   * 
   * @param fileNo ファイル番号
   * @return 該当のMarkdownファイルオブジェクト
   * @throws IllegalArgumentException fileNoが0以下の場合にスロー
   * @throws NotFoundException 該当ファイルが見つからない場合にスロー
   */
  public MarkdownFile findObjectByNo(int fileNo) {
    try {
      if (fileNo <= 0) {
        throw new IllegalArgumentException("ファイル番号は1以上を指定してください");
      }
      MarkdownFile file = grep.findByFileNo(fileNo);
      if (file == null) {
        throw new NotFoundException("番号: " + fileNo + " のファイルが見つかりません");
      }
      return file;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * mdファイルのリストを取得するメソッド
   * @return mdファイルのリスト
   */
  public List<String> listFiles() {
    try {
      return Files.list(Paths.get(MD_DIR))
              .filter(path -> path.toString().endsWith(".md"))
              .map(path -> path.getFileName().toString())
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException("ファイル見つからない", e);
    }
  }

  /**
   * 指定されたファイルを読み込むメソッド
   * @param filename
   * @return ファイルの内容を文字列として返す
   */
  public String readFile(String fileId) {
    try {
      Path path = Paths.get(MD_DIR, fileId);
      if (Files.exists(path)) {
        return Files.readString(path);
      }
      return "";
    } catch (IOException e) {
      throw new RuntimeException("ファイル読み込み失敗", e);
    }
  }

}
