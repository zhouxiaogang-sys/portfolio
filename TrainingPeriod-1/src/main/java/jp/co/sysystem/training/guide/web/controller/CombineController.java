package jp.co.sysystem.training.guide.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.service.CombineService;

@Controller
public class CombineController {

  @Autowired
  public CombineService combineService;

  public static final String COMBINE_PAGE = "page/combine";

  @GetMapping("/")
  public String showMenu(Model model) {
    List<MarkdownFile> markdownList = combineService.showIndex();

    model.addAttribute("markdowns", markdownList);
    model.addAttribute("content", "トップページへようこそ");
    return COMBINE_PAGE;
  }

  @GetMapping("/{fileId}")
  public String getContent(@PathVariable String fileId,
          HttpServletRequest request, Model model) {
    try {
      model.addAttribute("content", combineService.getHtmlContent(fileId));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    if (isAjaxRequest(request)) {
      return "page/combine :: content";
    }
    
    List<MarkdownFile> markdownList = combineService.showIndex();
    return COMBINE_PAGE;
  }

  private boolean isAjaxRequest(HttpServletRequest request) {
    return "XMLHttpRequest".equals(
            request.getHeader("X-Requested-With"));
  }

}
