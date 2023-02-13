package com.example.demo.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private String createtime;
    private String updatetime;
    private Integer state; //账号状态(冻结/正常)
}
