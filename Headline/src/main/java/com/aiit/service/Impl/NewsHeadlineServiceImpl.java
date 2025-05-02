package com.aiit.service.Impl;

import com.aiit.dao.Impl.NewsHeadlineDaoImpl;
import com.aiit.dao.NewsHeadlineDao;
import com.aiit.pojo.NewsHeadline;
import com.aiit.pojo.Vo.HeadlineDetailVo;
import com.aiit.pojo.Vo.HeadlinePageVo;
import com.aiit.pojo.Vo.HeadlineQueryVo;
import com.aiit.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hmxia
 * @date 2024/12/9 16:18
 */
public class NewsHeadlineServiceImpl implements NewsHeadlineService {

    private NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();

    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo) {
        //准备一个Map,用于存储五项数据
        Map<String,Object> pageInfo = new HashMap<>();
        //分页查询本页的数据：调用dao层的方法
        List<HeadlinePageVo> pageData =  newsHeadlineDao.findPageList(headlineQueryVo);
        //分页查询满足条件的总数据量
        int totalSize = newsHeadlineDao.findPageCount(headlineQueryVo);
        //页大小
        int pageSize = headlineQueryVo.getPageSize();
        //当前页码数
        int pageNum = headlineQueryVo.getPageNum();
        //总页数
        int totalPage = totalSize%pageSize == 0?totalSize/pageSize : totalSize/pageSize+1;

        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);

        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        //调用Dao层的方法
        //修改新闻信息的浏览量+1
        newsHeadlineDao.increasePageViews(hid);
        //查询新闻详情
        return newsHeadlineDao.findHeadlineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        //调用dao层的方法
        return newsHeadlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public int removeByHid(Integer hid) {
        //调用dao层的方法
        return newsHeadlineDao.removeByHid(hid);

    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        //调用Dao层的方法
        return newsHeadlineDao.findheadlineByHid(hid);
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        //调用Dao层的方法
        return newsHeadlineDao.updateNewsHeadline(newsHeadline);
    }
}
