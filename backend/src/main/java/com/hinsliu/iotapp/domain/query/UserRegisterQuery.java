package com.hinsliu.iotapp.domain.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @Description: Form object for register.
 * @author: liuxuanming
 * @date: 2021/03/30 4:23 下午
 */
@Data
public class UserRegisterQuery {

    @NotNull(message = "用户名不能为空")
    @Length(min = 6, message = "用户名长度至少需要6个字符")
    private String userName;

    @NotNull(message = "用户密码不能为空")
    @Length(min = 6, message = "密码长度至少需要6个字符")
    private String userPassword;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

}
