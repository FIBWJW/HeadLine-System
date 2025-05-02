package com.aiit.service;

import com.aiit.pojo.NewsHeadline;
import com.aiit.pojo.Vo.HeadlineDetailVo;
import com.aiit.pojo.Vo.HeadlineQueryVo;

import java.util.Map;

/**
 * @author hmxia
 * @date 2024/12/9 16:17
 */
public interface NewsHeadlineService {


    Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo);


    HeadlineDetailVo findHeadlineDetail(Integer hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);

    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);
}
