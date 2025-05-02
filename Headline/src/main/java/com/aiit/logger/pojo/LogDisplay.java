package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDisplay {
    private String action;//动作：曝光商品=1，点击商品=2，
    private String goodsid;//商品 ID（服务端下发的 ID）
    private String place;//顺序（第几条商品，第一条为 0，第二条为 1，如此类推）
    private String extend1;//曝光类型：1 - 首次曝光 2-重复曝光（没有使用）
    private String category;//分类 ID（服务端定义的分类 ID）

}
