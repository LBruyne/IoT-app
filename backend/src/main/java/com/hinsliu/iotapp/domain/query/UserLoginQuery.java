package com.hinsliu.iotapp.domain.query;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Description: Query for user login.
 * @author: liuxuanming
 * @date: 2021/03/28 9:25 上午
 */
@Data
public class UserLoginQuery {

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String userPassword;

}
