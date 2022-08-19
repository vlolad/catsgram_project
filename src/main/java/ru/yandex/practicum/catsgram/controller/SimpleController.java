package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SimpleController {

    private final static Logger log = LoggerFactory.getLogger(SimpleController.class);
    @GetMapping("/home")
    public String homePage() {
        log.info("Получен запрос.");
        return "Котограм";
    }
}
