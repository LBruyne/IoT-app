package com.hinsliu.iotapp.domain.view;

import lombok.Data;

/**
 * @Description: DTO for user info
 * @author: liuxuanming
 * @date: 2021/03/30 11:20 上午
 */
@Data
public class UserInfoDTO {

    private Integer id;

    private String userName;

    private String email;

    private String avatarUrl;

    private String token;

}
