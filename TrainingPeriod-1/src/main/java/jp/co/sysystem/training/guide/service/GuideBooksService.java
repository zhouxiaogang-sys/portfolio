package jp.co.sysystem.training.guide.service;

import jp.co.sysystem.training.guide.domain.dto.GuideBookDTO;
import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

@Service
public class GuideBooksService {

  private final Parser parser;
  private final HtmlRenderer renderer;

  private final String MD_DIR = "src/main/resources/markdown/";

  @Autowired
  private GuidesRepository grep;
  
  @Autowired
  private ModelMapper modelMapper;

  /**
   *markdownレンダラーの初期化 
   */
  public GuideBooksService() {
    MutableDataSet options = new MutableDataSet();
    options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));

    parser = Parser.builder(options).build();
    renderer = HtmlRenderer.builder(options).build();
  }

  public List<GuideBookDTO> showIndex() {
    List<MarkdownFile> results = grep.findAllByOrderBySortOrderAsc();
    return results.stream()
            .map(result -> modelMapper.map(result, GuideBookDTO.class))
            .collect(Collectors.toList());
  }

  /**
   * markdownファイルをレンダーする
   * @param fileId
   * @return
   * @throws IOException
   */
  public String getHtmlContent(String fileId) throws IOException {

    if (fileId.equals("favicon.ico")) {
      return null;
    }

    Path filePath = Paths.get(MD_DIR + fileId + ".md");
    String markdown = Files.readString(filePath);

    return renderer.render(parser.parse(markdown));
  }

}