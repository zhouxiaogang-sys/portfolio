package jp.co.sysystem.training.guide.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sysystem.training.guide.domain.dto.GuideBookDTO;
import jp.co.sysystem.training.guide.service.GuideBooksService;

@Controller
public class ManagementController {

  public static final String INDEX_PAGE = "page/index";

  public static final String MANAGEMENT_PAGE = "page/management/management";

  public static final String FRAGMENTS_URL = "page/management/fragments/";

  @Autowired
  private GuideBooksService guideBooksService;

  @GetMapping("/management")
  public String showMenu(Model model) {
    List<GuideBookDTO> markdownList = guideBooksService.showIndex();
    model.addAttribute("markdowns", markdownList);

    return MANAGEMENT_PAGE;
  }

  @GetMapping("/management/load-content")
  public String loadContent(@RequestParam String content, Model model) {

    if ("content-all-guides".equals(content)) {
      List<GuideBookDTO> markdownList = guideBooksService.showIndex();
      model.addAttribute("markdowns", markdownList);
    }

    return FRAGMENTS_URL + content + "::" + content;
  }

}
