package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public List<ArticleInfo> myArtList(Integer uid){
        return articleMapper.myArtList(uid);
    }

    public ArticleInfo selectById(Integer aid){
        return articleMapper.selectById(aid);
    }

    public int increaseAccess(Integer aid){
        return articleMapper.increaseAccess(aid);
    }

    public int blogDel(Integer aid){
        return articleMapper.blogDel(aid);
    }

    public int update(Integer aid, String title, String content){
        return articleMapper.update(aid,title,content);
    }

    public int save(String title, String content, Integer uid){
        return articleMapper.save(title,content,uid);
    }

    public List<ArticleInfo> selectPartArt(Integer limit, Integer offset){
        return articleMapper.selectPartArt(limit,offset);
    }

    public int getBlogCount(){
        return articleMapper.getBlogCount();
    }

}
