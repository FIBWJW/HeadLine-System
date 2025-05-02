package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogCart {
    int itemid;
    int action;	// 1 添加产品进购物车 2 调整购物车数量
    int changeNum; // 数量变化
    int beforeNum; // 变化前数量
    int afterNum; // 变化后数量
    Double price; // 加入购物车时的单价

}
