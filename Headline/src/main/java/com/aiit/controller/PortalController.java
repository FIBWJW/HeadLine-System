package com.aiit.controller;

import com.aiit.common.Result;
import com.aiit.pojo.NewsType;
import com.aiit.pojo.Vo.HeadlineDetailVo;
import com.aiit.pojo.Vo.HeadlineQueryVo;
import com.aiit.service.Impl.NewsHeadlineServiceImpl;
import com.aiit.service.Impl.NewsTypeServiceImpl;
import com.aiit.service.NewsHeadlineService;
import com.aiit.service.NewsTypeService;
import com.aiit.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hmxia
 * @date 2024/12/16 16:28
 * 新闻类型的获取
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{

    private NewsHeadlineService headlineService=new NewsHeadlineServiceImpl();

    private NewsTypeService newsTypeService = new NewsTypeServiceImpl();



    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数:获取要查询的新闻id
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        //2、调用service层的方法
        HeadlineDetailVo headlineDetailVo =headlineService.findHeadlineDetail(hid);
        //封装data的内容
        Map<String, Object> data = new HashMap<>();
        data.put("headline",headlineDetailVo);
        //3、设置响应数据
        WebUtil.writeJson(resp,Result.ok(data));
    }

    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数 （前端中findNewsPageInfo）
        /*
        {
        "keyWords":"马斯克", // 搜索标题关键字
        "type":0,           // 新闻类型
        "pageNum":1,        // 页码数
        "pageSize":"10"     // 页大小
        }
         */
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        //2、调用service层的方法
        Map<String,Object>  pageInfo = headlineService.findPage(headlineQueryVo);
        Map<String,Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);
        //3、将结果响应
        WebUtil.writeJson(resp,Result.ok(pageInfoMap));
    }

    //查询所有的新闻类型
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用service层的方法
        List<NewsType> newsTypeList  = newsTypeService.findAll();
        //将结果转化为json格式输出
        WebUtil.writeJson(resp, Result.ok(newsTypeList));

    }
}
