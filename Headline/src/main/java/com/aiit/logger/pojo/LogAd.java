package com.aiit.logger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/23 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAd {
    private String entry;//入口：商品列表页=1 应用首页=2 商品详情页=3
    private String action;//动作：请求广告=1 取缓存广告=2 =4 广告点击=5
    private String content;//状态：成功=1 失败=2
    private String detail;//失败码（没有则上报空）
    private String source;//广告来源:admob=1 facebook=2罗斯）=4
    private String behavior;//用户行为：	主动获取广告=1	被动获取广告=2
    private String newstype;//Type: 1- 图文 2-图集 3-段子 4-GIF 5-视频 6-调查 7-纯文 8-视频+图文 9-GIF+图文 0-其他
    private String show_style;//内容样式：无图(纯文字)=6 一张大图=1 三站小图+文=4 一张小图=2 一张大图两张小图+文=3 图集+文 = 5
//一张大图+文=11  GIF 大图+文=12 视频(大图)+文 = 13
//来源于详情页相关推荐的商品，上报样式都为 0（因为都是左文右图）

}
