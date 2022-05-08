package com.dmh.words.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SensitiveWords {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String words;

    private Integer status;
}
