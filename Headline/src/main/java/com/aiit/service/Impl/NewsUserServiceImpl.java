package com.aiit.service.Impl;

import com.aiit.dao.Impl.NewsUserDaoImpl;
import com.aiit.dao.NewsUserDao;
import com.aiit.pojo.NewsUser;
import com.aiit.service.NewsUserService;
import com.aiit.utils.MD5Util;

/**
 * @author hmxia
 * @date 2024/12/9 16:18
 */
public class NewsUserServiceImpl implements NewsUserService {

    NewsUserDao userDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUsername(String username) {

        //调用Dao层中的方法
        NewsUser user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        //调用Dao层的方法
        NewsUser user = userDao.findByUid(userId);
        return user;
    }

    @Override
    public int registUser(NewsUser newsUser) {
        //密码明文转为密文
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        //调用Dao层的方法，将数据写入数据库中
        return userDao.insertNewsUser(newsUser);
    }

}
