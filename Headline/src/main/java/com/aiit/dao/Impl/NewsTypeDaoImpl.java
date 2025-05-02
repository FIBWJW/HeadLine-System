package com.aiit.dao.Impl;

import com.aiit.dao.BaseDao;
import com.aiit.dao.NewsTypeDao;
import com.aiit.pojo.NewsType;

import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:14
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {


    @Override
    public List<NewsType> findAll() {
        //实现从数据库中查询所有的新闻类型
        String sql = "select tid,tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
