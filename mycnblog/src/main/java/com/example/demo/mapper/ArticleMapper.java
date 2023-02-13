package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作文章数据的mapper接口
 */
@Mapper
public interface ArticleMapper {
    public List<ArticleInfo> myArtList(@Param("uid") Integer uid);
    public ArticleInfo selectById(@Param("aid") Integer aid);
    public int increaseAccess(@Param("aid") Integer aid);
    public int blogDel(@Param("aid") Integer aid);
    public int update(@Param("aid") Integer aid, @Param("title") String title, @Param("content") String content);
    public int save(@Param("title") String title, @Param("content") String content, @Param("uid") Integer uid);
    public List<ArticleInfo> selectPartArt(Integer limit, Integer offset);
    public int getBlogCount();
}
