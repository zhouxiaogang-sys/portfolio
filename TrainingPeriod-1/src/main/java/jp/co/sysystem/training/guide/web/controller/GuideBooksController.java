package jp.co.sysystem.training.guide.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jp.co.sysystem.training.guide.domain.dto.GuideBookDTO;
import jp.co.sysystem.training.guide.service.GuideBooksService;
import jp.co.sysystem.training.guide.util.MessageUtil;

@Controller
public class GuideBooksController {

  @Autowired
  public GuideBooksService guideBooksService;
  
  @Autowired
  public MessageUtil msgutil;

  private static final String HOME_PAGE = "page/guide-books";

  private static final String CONTENT_FRAGMENT = "page/guide-books :: content";

  @GetMapping("/guide-books")
  public String showMenu(Model model) throws IOException {
    try {
      List<GuideBookDTO> markdownList = guideBooksService.showIndex();
      model.addAttribute("markdowns", markdownList);
      if (!model.containsAttribute("content")) {
        model.addAttribute("content", guideBooksService.getHtmlContent("index"));
      }
      return HOME_PAGE;
    } catch (IOException e) {
      String msg = msgutil.getMessage("server.failure");
      model.addAttribute("errorMessage", msg);
      return "error/500";
    }
  }

  @GetMapping("/content/{fileId}")
  public String getContent(@PathVariable String fileId,
          HttpServletRequest request,
          Model model) {
    try {
      String htmlContent = guideBooksService.getHtmlContent(fileId);
      model.addAttribute("content", htmlContent);

      return CONTENT_FRAGMENT;

    } catch (IOException e) {
      model.addAttribute("content", "Error loading content");
      return CONTENT_FRAGMENT;
    }
  }

//  private void setupHomePageModel(Model model) throws IOException {
//    List<MarkdownFile> markdownList = guideBooksService.showIndex();
//    LoginForm loginForm = new LoginForm();
//
//    model.addAttribute("markdowns", markdownList);
//    model.addAttribute("loginForm", loginForm);
//
//    if (!model.containsAttribute("content")) {
//      model.addAttribute("content", guideBooksService.getHtmlContent("index"));
//    }
//  }

}
