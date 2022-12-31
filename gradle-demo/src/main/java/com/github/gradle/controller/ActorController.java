package com.github.gradle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @GetMapping("/index")
    public String index() {
        return """
                hello, gradle
                <h1> hello, gradle <h1>
                """;
    }
}
