package com.aiit.dao.Impl;

import com.aiit.dao.BaseDao;
import com.aiit.dao.NewsUserDao;
import com.aiit.pojo.NewsUser;

import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:13
 */
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {


    @Override
    public NewsUser findByUsername(String username) {
        String sql = "select uid,username,user_pwd userPwd,nick_name nickName from news_user where username= ?";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        if(newsUserList !=null && newsUserList.size()>0){
            return newsUserList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = "select uid,username,user_pwd userPwd,nick_name nickName from news_user where uid= ?";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        if(newsUserList !=null && newsUserList.size()>0){
            return newsUserList.get(0);


        }else{
            return null;
        }
    }

    @Override
    public int insertNewsUser(NewsUser newsUser) {
        String sql = "insert into news_user values(DEFAULT,?,?,?)";
        return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());

    }


}
