package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogNotification {
    private String action;//动作：通知产生=1，通知弹出=2，通知点击=3，常驻通知展示（不重复上报，一天之内只报一次）=4
    private String type;//通知 id：预警通知=1，天气预报（=2，晚=3），常驻=4
    private String ap_time;//客户端弹出时间
    private String content;//备用字段

}
