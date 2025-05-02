package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogComment {
    private int comment_id;//评论表
    private int userid;//用户 id
    private int p_comment_id;//父级评论 id(为 0 则是一级评论,不为 0 则是回复)
    private String content;//评论内容
    private String addtime;//创建时间
    private int other_id;//评论的相关 id
    private int praise_count;//点赞数量
    private int reply_count;//回复数量

}
