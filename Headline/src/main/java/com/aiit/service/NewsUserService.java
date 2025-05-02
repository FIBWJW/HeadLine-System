package com.aiit.service;

import com.aiit.pojo.NewsUser;

/**
 * @author hmxia
 * @date 2024/12/9 16:17
 */
public interface NewsUserService {

    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);


    int registUser(NewsUser newsUser);
}
