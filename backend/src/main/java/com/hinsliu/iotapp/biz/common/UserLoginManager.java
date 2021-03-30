package com.hinsliu.iotapp.biz.common;

import com.hinsliu.iotapp.biz.utils.redis.RedisUtil;
import com.hinsliu.iotapp.biz.utils.token.TokenGenerator;
import com.hinsliu.iotapp.dal.common.UserInfoDao;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.model.common.UserInfoDO;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.view.common.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: manager for user login and register
 * @author: liuxuanming
 * @date: 2021/03/25 2:29 下午
 */
@Service
public class UserLoginManager {
    /**
     *  Login Design:
     *  Once the user log into the system, we use uid to generate a unique String, which is token.
     *  Then sava the key-value pair into REDIS, where key is the token and value is the serialized string of User Object.
     *  When user is visiting other page, we check if the the parameters or the Cookie has token,
     *  if it is, check the validation of token
     *  if not, throw the exception: user didn't login
     */
    private static Logger logger = LoggerFactory.getLogger(UserLoginManager.class);

    @Resource
    private UserInfoDao userInfoDao;

    /**
     * @description: validate the login info.
     * @author: liuxuanming
     * @date: 2021/3/30 4:16 下午
     * @params: [userInfo]
     * @return: com.hinsliu.iotapp.domain.RpcResult<com.hinsliu.iotapp.domain.view.common.UserInfoDTO>
     */
    public RpcResult<UserInfoDTO> loginValidation(UserLoginQuery userInfo) {
        RpcResult<UserInfoDTO> rpcResult = RpcResult.errorResult("User not found.");
        String userName = userInfo.getUserName(), userPassword = userInfo.getUserPassword();
        if(userName == null || userPassword == null) return rpcResult;
        try {
            UserInfoDO user = userInfoDao.queryByInfo(userInfo);
            UserInfoDTO userDto = new UserInfoDTO();
            // could find the user.
            // generate the token and return.
            if(user != null) {
                RedisUtil redis = RedisUtil.getInstance();
                String token = TokenGenerator.generate(userName, userPassword);
                redis.set(token, userName);

                // put the token into result.
                BeanUtils.copyProperties(user, userDto);
                userDto.setToken(token);
                rpcResult.setSuccess(true, userDto);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return rpcResult;
    }
    
    /**
     * @description: callback for redirection to /app/callback
     * @author: liuxuanming
     * @date: 2021/3/30 4:34 下午
     * @params: []
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    public RpcResult<String> unauthorizedCallback() {
        return RpcResult.errorResult("Unauthorized.");
    }
}
