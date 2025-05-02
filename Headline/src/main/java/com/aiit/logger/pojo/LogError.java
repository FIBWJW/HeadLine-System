package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogError {
    private String errorBrief;	//错误摘要
    private String errorDetail;  //错误详情
}
