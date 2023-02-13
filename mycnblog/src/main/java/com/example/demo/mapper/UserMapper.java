package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作用户数据的mapper接口
 */
@Mapper
public interface UserMapper {
    public int add(@Param("username") String username, @Param("password") String password);
    public UserInfo login(@Param("username") String username, @Param("password") String password);
    public UserInfo selectById(@Param("uid") Integer aid);

    UserInfo getUserByName(@Param("username") String username);
}
