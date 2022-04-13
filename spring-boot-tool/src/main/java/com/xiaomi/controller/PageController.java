package com.xiaomi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {

    @GetMapping({"/index.html", "/"})
    public String index() {
        return "upload";
    }

    @GetMapping({"/upload.html", "/upload"})
    public String upload() {
        return "index";
    }

}
