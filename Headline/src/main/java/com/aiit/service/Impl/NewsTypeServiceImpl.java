package com.aiit.service.Impl;

import com.aiit.dao.Impl.NewsTypeDaoImpl;
import com.aiit.dao.NewsTypeDao;
import com.aiit.pojo.NewsType;
import com.aiit.service.NewsTypeService;

import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 16:18
 */
public class NewsTypeServiceImpl implements NewsTypeService {

    private NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();


    @Override
    public List<NewsType> findAll() {
        //调用Dao层的方法
        return newsTypeDao.findAll();
    }
}
