package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 9:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogStart extends BaseLog {
    private String entry;//入口： push=1，widget=2，icon=3，notification=4, lockscreen_widget =5
    private String open_ad_type;//开屏广告类型: 开屏原生广告=1, 开屏插屏广告=2
    private String action;//状态：成功=1 失败=2
    private String loading_time;//加载时长：计算下拉开始到接口返回数据的时间，（开始加载报 0，加载成功或加载失败才上报时间）
    private String detail;//失败码（没有则上报空）
    private String extend1;//失败的 message（没有则上报空）
    private String en;//启动日志类型标记

}
