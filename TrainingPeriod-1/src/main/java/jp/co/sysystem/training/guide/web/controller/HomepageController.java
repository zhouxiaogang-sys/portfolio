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
import jp.co.sysystem.training.guide.service.HomepageService;
import jp.co.sysystem.training.guide.web.form.LoginForm;

@Controller
public class HomepageController {

  @Autowired
  public HomepageService homepageService;

  public static final String HOME_PAGE = "page/homepage";
  private static final String CONTENT_FRAGMENT = "page/homepage :: content";

  @GetMapping("/")
  public String showMenu(Model model) throws IOException {
    try {
      setupHomePageModel(model);
      return HOME_PAGE;
    } catch (IOException e) {
      return "error/500";
    }
  }

  @GetMapping("/content/{fileId}")
  public String getContent(@PathVariable String fileId,
          HttpServletRequest request,
          Model model) {
    try {
      String htmlContent = homepageService.getHtmlContent(fileId);
      model.addAttribute("content", htmlContent);

      return CONTENT_FRAGMENT;

    } catch (IOException e) {
      model.addAttribute("content", "Error loading content");
      return CONTENT_FRAGMENT;
    }
  }

  private void setupHomePageModel(Model model) throws IOException {
    List<MarkdownFile> markdownList = homepageService.showIndex();
    LoginForm loginForm = new LoginForm();

    model.addAttribute("markdowns", markdownList);
    model.addAttribute("loginForm", loginForm);

    if (!model.containsAttribute("content")) {
      model.addAttribute("content", homepageService.getHtmlContent("index"));
    }
  }

}
