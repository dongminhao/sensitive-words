package com.dmh.words;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.dmh.words.mapper"})
@SpringBootApplication
public class WordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordsApplication.class, args);
    }

}
