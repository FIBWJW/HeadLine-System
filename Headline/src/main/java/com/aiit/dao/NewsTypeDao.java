package com.aiit.dao;

import com.aiit.pojo.NewsType;

import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:13
 */
public interface NewsTypeDao {

    List<NewsType> findAll();
}
