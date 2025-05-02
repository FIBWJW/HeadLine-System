package com.aiit.dao;

import com.aiit.pojo.NewsUser;

/**
 * @author hmxia
 * @date 2024/12/9 15:13
 */
public interface NewsUserDao {

    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);


    int insertNewsUser(NewsUser newsUser);
}
