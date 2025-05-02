package com.aiit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hmxia
 * @date 2024/12/9 15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsUser {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
