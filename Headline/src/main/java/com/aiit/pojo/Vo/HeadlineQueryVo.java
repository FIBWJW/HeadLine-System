package com.aiit.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/17 9:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadlineQueryVo {
    private String keyWords;
    private Integer type;
    private Integer pageNum;
    private Integer pageSize;

}
