package jp.co.sysystem.training.guide.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.sysystem.training.guide.service.ViewerService;

//@Controller
public class ViewerController {

    @Autowired
    public ViewerService viewerService;

    public static final String VIEWER_PAGE = "page/viewer";

    @GetMapping("/{fileId}")
    public String showPage(@PathVariable String fileId, Model model) {

        try {
            model.addAttribute("content", viewerService.getHtmlContent(fileId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return VIEWER_PAGE;
    }

}
