package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo implements Serializable {
    private Integer id;
    private String title; //标题
    private String content; //正文
    private String createtime;
    private String updatetime;
    private Integer uid; //外键,userId
    private Integer rcount; //访问次数
    private Integer state; //文章是已发布状态还是草稿状态
}
