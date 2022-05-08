package com.dmh.words.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmh.words.entity.SensitiveWords;
import com.dmh.words.mapper.SensitiveWordsMapper;
import com.dmh.words.sensitive.SensitiveWordsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SensitiveWordsInit implements CommandLineRunner {

    private final SensitiveWordsMapper sensitiveWordsMapper;

    private final SensitiveWordsUtils sensitiveWordsUtils;


    @Override
    public void run(String... args) throws Exception {
        sensitiveWordsUtils.initTree(sensitiveWordsMapper.selectList(Wrappers.<SensitiveWords>lambdaQuery().eq(SensitiveWords::getStatus, 1)).stream().map(SensitiveWords::getWords).collect(Collectors.toList()));
    }
}
