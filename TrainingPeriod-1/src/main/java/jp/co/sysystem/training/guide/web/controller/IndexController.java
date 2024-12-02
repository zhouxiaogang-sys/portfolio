package jp.co.sysystem.training.guide.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.service.IndexService;

//@Controller
public class IndexController {
  
  public static final String MENU_PAGE = "page/indexMenu";
  
  @Autowired
  private IndexService indexService;
  
  @GetMapping("/")
  public String showMenu(Model model) {
    List<MarkdownFile>markdownList = indexService.showIndex();
    
    model.addAttribute("markdowns", markdownList);
    return MENU_PAGE;
  }

}
