package com.hinsliu.iotapp.dal.common;

import com.hinsliu.iotapp.domain.model.UserInfoDO;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.query.UserRegisterQuery;
import org.springframework.stereotype.Repository;

/**
 * @Description: DAO for user info.
 * @author: liuxuanming
 * @date: 2021/03/27 9:43 下午
 */
public interface UserInfoDao {

    public UserInfoDO query(UserLoginQuery query);

    public UserInfoDO queryByRegister(UserRegisterQuery query);

    public Integer insert(UserInfoDO query);

}
