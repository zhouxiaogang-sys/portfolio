package jp.co.sysystem.training.guide.service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ViewerService {
    private final Parser parser;
    private final HtmlRenderer renderer;

    private final String MD_DIR = "src/main/resources/markdown/";

    /**
     *markdownレンダラーの初期化 
     */
    public ViewerService() {
        MutableDataSet options = new MutableDataSet();
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    /**
     * markdownファイルをレンダーする
     * @param filename
     * @return
     * @throws IOException
     */
    public String getHtmlContent(String filename) throws IOException {
        
        if (filename.equals("favicon.ico")) {
            return null;
        }

        Path filePath = Paths.get(MD_DIR + filename + ".md");
        String markdown = Files.readString(filePath);
        return renderer.render(parser.parse(markdown));
    }
}