package com.example.demo.common;

import com.example.demo.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 返回当前登录的 Session 用户信息
 */

public class SessionUtil {
    public static UserInfo getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(Constant.SESSION_USERINFO_KEY) != null){
            return (UserInfo) session.getAttribute(Constant.SESSION_USERINFO_KEY);
        }

        return null;
    }
}
