package jp.co.sysystem.training.guide.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.service.HomepageService;

@Controller
public class ManagementController {
  
  public static final String MANAGEMENT_PAGE = "page/management";
  
  @Autowired
  private HomepageService homepageService;
  
  @GetMapping("/management")
  public String showMenu(Model model) {
    List<MarkdownFile>markdownList = homepageService.showIndex();
    
    model.addAttribute("markdowns", markdownList);
    return MANAGEMENT_PAGE;
  }

}
