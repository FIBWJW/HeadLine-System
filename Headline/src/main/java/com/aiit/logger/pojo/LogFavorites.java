package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogFavorites {
    private int id;//主键
    private int course_id;//商品 id
    private int userid;//用户 ID
    private String add_time;//创建时间

}
