package jp.co.sysystem.training.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditorService {
  
  @Autowired
  GuidesRepository grep;

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
    
    public String findNameById(String fileId) {
      Optional<MarkdownFile> optionalFile = grep.findById(fileId);
      String fileName = optionalFile.map(MarkdownFile::getFileName).orElse(null);
      return fileName;
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
    public String readFile(String filename) {
        try {
            Path path = Paths.get(MD_DIR, filename);
            if (Files.exists(path)) {
                return Files.readString(path);
            }
            return "";
        } catch (IOException e) {
            throw new RuntimeException("ファイル読み込み失敗", e);
        }
    }

    /**
     * // ファイルを保存するメソッド
     * @param filename
     * @param content
     */
    public void saveFile(String filename, String content) {
        try {
            if (!filename.endsWith(".md")) {
                filename += ".md";
            }
            Path path = Paths.get(MD_DIR, filename);
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new RuntimeException("保存失敗", e);
        }
    }
}
