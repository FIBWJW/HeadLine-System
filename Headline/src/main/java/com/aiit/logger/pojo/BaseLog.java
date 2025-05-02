package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 9:58
 * 公共日志
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseLog {
    private String mid; // (String) 设备唯一标识
    private String uid; // (String) 用户 uid
    private String vc; // (String) versionCode，程序版本号
    private String vn; // (String) versionName，程序版本名
    private String l; // (String) 系统语言
    private String sr; // (String) 渠道号，应用从哪个渠道来的。
    private String ar; // (String) 区域
    private String sv; // (String) sdkVersion
    private String g;	// (String) gmail
    private String t;	// (String) 客户端日志产生时的时间
    private String nw; // (String) 网络模式
    private String ln; // (double) lng 经度
    private String la; // (double) lat 纬度

}
