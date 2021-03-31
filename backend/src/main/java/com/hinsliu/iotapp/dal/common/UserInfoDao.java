package com.hinsliu.iotapp.dal.common;

import com.hinsliu.iotapp.domain.model.common.UserInfoDO;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.query.UserRegisterForm;
import org.springframework.stereotype.Repository;

/**
 * @Description: DAO for user info.
 * @author: liuxuanming
 * @date: 2021/03/27 9:43 下午
 */
@Repository
public interface UserInfoDao {

    /**
     * @description: select by primary key
     * @author: liuxuanming
     * @date: 2021/3/30 11:42 上午
     * @params: [id]
     * @return: com.hinsliu.iotapp.domain.model.common.UserInfoDO
     */
    public UserInfoDO selectByPrimaryKey(Integer id);

    /**
     * @description: Query if the login is valid
     * @author: liuxuanming
     * @date: 2021/3/30 10:49 上午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.model.common.UserInfoDO
     */
    public UserInfoDO queryByInfo(UserLoginQuery query);

    /**
     * @description: check if the username or the email existed.
     * @author: liuxuanming
     * @date: 2021/3/31 12:07 上午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.model.common.UserInfoDO
     */
    public UserInfoDO queryByRegisterForm(UserRegisterForm query);

    /**
     * @description: insert new user info into table.
     * @author: liuxuanming
     * @date: 2021/3/31 5:46 下午
     * @params: [query]
     * @return: java.lang.Integer
     */
    public Integer insert(UserInfoDO query);
    
    
}
