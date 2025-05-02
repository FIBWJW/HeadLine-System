package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPraise {
    private int id; //主键 id
    private int userid;//用户 id
    private int target_id;//点赞的对象 id
    private int type;//点赞类型 1 问答点赞 2 问答评论点赞 3 文章点赞数 4 评论点赞
    private String add_time;//添加时间

}
