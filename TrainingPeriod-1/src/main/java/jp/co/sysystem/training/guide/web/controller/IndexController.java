package jp.co.sysystem.training.guide.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

  private static final String INDEX = "page/index";

  @GetMapping("/")
  public String showIndex(Model model) {

    return INDEX;
  }

}
