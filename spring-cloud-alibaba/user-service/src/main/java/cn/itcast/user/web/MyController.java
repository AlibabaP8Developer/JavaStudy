package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("my")
public class MyController {

    @Autowired
    private PatternProperties dateFormat;

    @GetMapping("/prop")
    public PatternProperties prop() {
        return dateFormat;
    }

    @GetMapping("/config")
    public String config() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat.getDateFormat()));
    }

}
