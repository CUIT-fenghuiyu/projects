package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章控制器
 */

@RestController
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 得到登录用户发表的所有博客
     * @param request
     * @return
     */
    @RequestMapping("/mylist")
    public List<ArticleInfo> mylist(HttpServletRequest request){
        List<ArticleInfo> artList = null;
        //得到登录用户信息
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo != null){
            //得到登录用户的 id
            int uid = userInfo.getId();
            artList = articleService.myArtList(uid);
        }

        return artList;
    }

    /**
     * 得到博客的详细信息
     * @param aid
     * @return
     */
    @RequestMapping("/detail")
    public Object detail(Integer aid){
        if(aid!=null && aid>0){
            //博客访问量+1
            ArticleInfo articleInfo = articleService.selectById(aid);
            if(articleInfo != null){
                articleService.increaseAccess(aid);
                return AjaxResult.success(articleService.selectById(aid));
            }
        }

        return AjaxResult.fail(-1,"查询失败");
    }

    /**
     * 删除博客功能
     * @param aid
     * @return
     */
    @RequestMapping("/blogDel")
    public Object blogDel(HttpServletRequest request, Integer aid){
        if(aid != null && aid>0){
            ArticleInfo articleInfo = articleService.selectById(aid);
            //判断该博客是否属于已登录的用户
            if(articleInfo!=null && articleInfo.getUid().equals(SessionUtil.getLoginUser(request).getId())){
                return AjaxResult.success(articleService.blogDel(aid));
            }
        }

        return AjaxResult.fail(-1,"删除失败");
    }

    /**
     * 得到要修改博客的信息
     * @param aid
     * @return
     */
    @RequestMapping("/detailbyid")
    public Object getDetailbyid(HttpServletRequest request,Integer aid){
        if(aid != null && aid>0){
            //根据id查找博客
            ArticleInfo articleInfo = articleService.selectById(aid);
            //判断该博客是否属于已登录的用户
            if(articleInfo!=null && articleInfo.getUid().equals(SessionUtil.getLoginUser(request).getId())){
                return AjaxResult.success(articleInfo);
            }
        }

        return AjaxResult.fail(-1,"查询失败");
    }

    /**
     * 修改博客提交到数据库
     * @param aid
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/update")
    public Object update(Integer aid, String title, String content){
        //非空校验
        if(aid!=null && aid>0 && StringUtils.hasLength(title) && StringUtils.hasLength(content)){
            return AjaxResult.success(articleService.update(aid,title,content));
        }

        return AjaxResult.fail(-1,"修改失败");
    }

    /**
     * 发布文章功能
     * @param request
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/save")
    public Object save(HttpServletRequest request, String title, String content){
        //得到登录用户
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        //非空校验
        if(userInfo!=null && StringUtils.hasLength(title) && StringUtils.hasLength(content)){
            //查询用户id
            Integer uid = userInfo.getId();
            return AjaxResult.success(articleService.save(title,content,uid));
        }

        return AjaxResult.fail(-1,"发布失败");
    }

    /**
     * 得到一页的博客数据
     * @param pindex 页码
     * @param psize 每页的条数
     * @return
     */
    @RequestMapping("/list")
    public Object getPartList(Integer pindex, Integer psize){
        if(pindex!=null && psize!=null){
            // 计算limit和offset的值
            Integer limit = psize;
            Integer offset = psize*(pindex-1);
            return AjaxResult.success(articleService.selectPartArt(limit,offset));
        }

        return AjaxResult.fail(-1,"查询失败");
    }

    /**
     * 计算发表的博客一共有多少页
     * @param psize
     * @return
     */
    @RequestMapping("/totalpage")
    public Integer getTotalPage(Integer psize){
        if(psize!=null){
            int blogCount = articleService.getBlogCount();
            int totalPage = (int)Math.ceil(blogCount*1.0/psize); //通过Math.ceil(小数会转换成大的整数)计算出总页数

            return totalPage;
        }

        return null;
    }
}
