package jp.co.sysystem.training.guide.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.sysystem.training.guide.service.LoginService;
import jp.co.sysystem.training.guide.util.MessageUtil;
import jp.co.sysystem.training.guide.web.form.LoginForm;

@Controller
public class LoginController {

  @Autowired
  public MessageUtil msgutil;
  
  @Autowired
  HttpSession session;
  
  @Autowired
  public LoginService loginService;
  
  public static final String LOGIN_FORM_URL = "/login";
  
  public static final String LOGOUT_URL = "/logout";
  
  public static final String REDIRECT_URL = "redirect:/management";
  
  public static final String FAIL_URL = "redirect:/";
  
      
  @PostMapping("/login")
  public  String loginProcess(@Validated @ModelAttribute LoginForm form,
          BindingResult bindingResult,
          Model model) {
    if (bindingResult.hasErrors()) {
      String msg = msgutil.getMessage("login.failure");
      model.addAttribute("msg", msg);
      model.addAttribute("loginForm", form);
      return FAIL_URL;
    }
    
    return loginService.checkLoginUser(form.getUsername(), form.getPassword())
            .map(user -> {
              session.setAttribute("username", form.getUsername());
              model.addAttribute("currentUser", user);
              return REDIRECT_URL;
            })
            .orElseGet(() -> {
              model.addAttribute("msg", "login.failure");
              return FAIL_URL;
            });
    
  }
  
  @GetMapping(LOGOUT_URL)
  public String logout(Model model) {
    session.invalidate();
    model.addAttribute("loginForm", new LoginForm());
    return FAIL_URL;
  }
}
