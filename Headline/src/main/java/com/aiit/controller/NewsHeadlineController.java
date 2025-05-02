package com.aiit.controller;

import com.aiit.common.Result;
import com.aiit.pojo.NewsHeadline;
import com.aiit.service.Impl.NewsHeadlineServiceImpl;
import com.aiit.service.NewsHeadlineService;
import com.aiit.utils.JwtHelper;
import com.aiit.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hmxia
 * @date 2024/12/9 16:20
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    //发布新闻的方法

    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        System.out.println("============="+req.toString());
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        System.out.println(newsHeadline);
        //通过token获取发布者的Id
        String token = req.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        newsHeadline.setPublisher(userId.intValue());
        //2、调用service层的方法
        headlineService.addNewsHeadline(newsHeadline);
        //3、设置响应数据
        WebUtil.writeJson(resp,Result.ok(null));
    }

    //删除新闻的方法
    protected void removebyHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        //2、调用service层的方法
        headlineService.removeByHid(hid);
        //3、设置响应数据
        WebUtil.writeJson(resp,Result.ok(null));
    }


    //修改新闻的方法
    //1、新闻回显
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        //2、调用service层的方法
        NewsHeadline newsHeadline = headlineService.findHeadlineByHid(hid);
        //3、设置响应数据
        /*
        result：{
        code:200,
        message:"success"
        data:{
        headline:{
            title:
            article:
            type:
            hid:
        }
        }
        }
        * */
        Map<String, Object> data = new HashMap<>();
        data.put("headline",newsHeadline);
        WebUtil.writeJson(resp,Result.ok(data));
    }

    //2、保存修改
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        //2、调用service层的方法
        headlineService.updateNewsHeadline(newsHeadline);
        //3、设置响应数据
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
