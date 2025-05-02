package com.aiit.dao.Impl;

import com.aiit.dao.BaseDao;
import com.aiit.dao.NewsHeadlineDao;
import com.aiit.pojo.NewsHeadline;
import com.aiit.pojo.Vo.HeadlineDetailVo;
import com.aiit.pojo.Vo.HeadlinePageVo;
import com.aiit.pojo.Vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:14
 */
public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {


    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = "select hid,title,type,page_views pageViews," +
                "TIMESTAMPDIFF(HOUR,create_time,NOW()) " +
                "pastHours,publisher from news_headline " +
                "where is_deleted=0";
        if(headlineQueryVo.getType()!=0){
            sql = sql.concat(" and type= ? ");
            params.add(headlineQueryVo.getType());
        }
        if(headlineQueryVo.getKeyWords()!= null&&!headlineQueryVo.getKeyWords().equals("")){
            sql = sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }
        sql = sql.concat(" order by pastHours ASC,page_views DESC ");
        sql = sql.concat(" limit ?,? ");   //在m+1页显示n条数据，还要告诉数据库，从哪条数据开始展示数据    1  10   id =  11-20
        params.add((headlineQueryVo.getPageNum()-1)*headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());

        return baseQuery(HeadlinePageVo.class, sql, params.toArray());



    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = "select count(1) from news_headline " +
                "where is_deleted=0";
        if(headlineQueryVo.getType()!=0){
            sql = sql.concat(" and type= ? ");
            params.add(headlineQueryVo.getType());
        }
        if(headlineQueryVo.getKeyWords()!= null&&!headlineQueryVo.getKeyWords().equals("")){
            sql = sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }


        Long count=  baseQueryObject(Long.class, sql, params.toArray());
        return count.intValue();
    }

    //修改访问量
    @Override
    public int increasePageViews(Integer hid) {
        String sql = "update news_headline set page_views = page_views +1 where hid=?";
        return baseUpdate(sql, hid);
    }

    //进行多表连接，查看新闻的详情数据
    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        String sql = "select hid,title,article,type,tname typeName,page_views pageViews," +
                "TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher,nick_name author\n" +
                "from news_headline h \n" +
                "LEFT JOIN news_type t\n" +
                "on h.type=t.tid\n" +
                "LEFT JOIN news_user u\n" +
                "on h.publisher = u.uid\n" +
                "where hid = ?";
        List<HeadlineDetailVo> headlineDetailVoList = baseQuery(HeadlineDetailVo.class, sql, hid);
        if(headlineDetailVoList!=null && headlineDetailVoList.size()>0){
            return headlineDetailVoList.get(0);
        }
        return null;
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "insert into news_headline values(DEFAULT,?,?,?,?,0,NOW(),NOW(),0)";
        return baseUpdate(sql,newsHeadline.getTitle(),
                newsHeadline.getArticle(),newsHeadline.getType(),
                newsHeadline.getPublisher()
        );
    }

    //逻辑删除：在数据库中并没有真正的删除该条数据，而是将其状态修改成了不可见（update）
    //物理删除：在数据库中真正的删除了该条数据(delete)
    //这里使用的是逻辑删除
    @Override
    public int removeByHid(Integer hid) {
        String sql="update news_headline set is_deleted = 1,update_time = NOW() where hid=?";
        return baseUpdate(sql, hid);
    }

    @Override
    public NewsHeadline findheadlineByHid(Integer hid) {
        String sql = "select hid,title,article,type,publisher,page_views pageViews " +
                "from news_headline " +
                "where hid = ?";
        List<NewsHeadline> newsHeadlineList = baseQuery(NewsHeadline.class, sql, hid);
        if(newsHeadlineList!=null && newsHeadlineList.size()>0){
            return newsHeadlineList.get(0);
        }
        return null;
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        String sql="update news_headline " +
                "set title = ?,article = ?,type=?,update_time = NOW() " +
                "where hid=?";
        return baseUpdate(sql,
                newsHeadline.getTitle(),
                newsHeadline.getArticle(),
                newsHeadline.getType(),
                newsHeadline.getHid());
    }


}
