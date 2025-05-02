package com.aiit.dao;

import com.aiit.pojo.NewsHeadline;
import com.aiit.pojo.Vo.HeadlineDetailVo;
import com.aiit.pojo.Vo.HeadlinePageVo;
import com.aiit.pojo.Vo.HeadlineQueryVo;

import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:12
 */
public interface NewsHeadlineDao {


    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    int findPageCount(HeadlineQueryVo headlineQueryVo);


    int increasePageViews(Integer hid);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);

    NewsHeadline findheadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);
}
