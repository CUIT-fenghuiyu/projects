package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.Constant;
import com.example.demo.common.SecurityUtil;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * 用户控制器
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/reg")
    public Object reg(String username, String password){
        // 非空校验
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return AjaxResult.fail(-1,"非法参数请求");
        }

        // 将密码进行加盐加密后，把用户数据添加到数据库
        int result = userService.add(username, SecurityUtil.encrypt(password));
        if(result==1){
            return AjaxResult.success("注册成功","1");
        }else {
            return AjaxResult.fail(-1,"数据库添加失败");
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 如果用户和密码都正确，返回1；如果用户名或密码为空，或不正确，那么返回非1
     */
    @RequestMapping("/login")
    public int login(HttpServletRequest request, String username, String password){
        // 非空校验
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            //参数不完整
            return 0;
        }

        // 数据库查询匹配登录
        UserInfo userInfo = userService.getUserByName(username);
        if(userInfo==null || userInfo.getId() <= 0 || !SecurityUtil.decrypt(password,userInfo.getPassword())){
            //用户名或者密码错误
            return -1;
        }else {
            // 用户名和密码正确,登录成功,将 userinfo 保存到 session 中
            HttpSession session = request.getSession(true);
            session.setAttribute(Constant.SESSION_USERINFO_KEY,userInfo);

            return 1;
        }
    }

    /**
     * 退出功能
     * @param request
     * @return 退出成功返回true
     */
    @RequestMapping("/logout")
    public Boolean logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(Constant.SESSION_USERINFO_KEY) != null){
            // 删除 Session 中已登录的用户
            session.removeAttribute(Constant.SESSION_USERINFO_KEY);

            return true;
        }

        return false;
    }

    /**
     * 通过 Session 得到登录用户信息
     * @param request
     * @return
     */
    @RequestMapping("/myinfo")
    public UserInfo myinfo(HttpServletRequest request){
        UserInfo userInfo = SessionUtil.getLoginUser(request);

        return userInfo;
    }

    /**
     *
     * @param uid
     * @return
     */
    @RequestMapping("/myinfobyuid")
    public Object myinfobyuid(Integer uid){
        if(uid != null && uid>0){
            UserInfo userInfo = userService.selectById(uid);
            if(userInfo!=null){
                return AjaxResult.success(userService.selectById(uid));
            }
        }

        return AjaxResult.fail(-1,"用户信息查询失败");
    }
}
