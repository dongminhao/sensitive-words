package com.dmh.words.controller;

import com.dmh.words.sensitive.SensitiveWordsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final SensitiveWordsUtils sensitiveWordsUtils;

    @GetMapping("/filter")
    public String filter(String text) {
        return sensitiveWordsUtils.filter(text, "*");
    }
}
