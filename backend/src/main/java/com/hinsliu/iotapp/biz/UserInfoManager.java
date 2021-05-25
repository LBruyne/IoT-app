package com.hinsliu.iotapp.biz;

import com.hinsliu.iotapp.biz.utils.redis.RedisUtil;
import com.hinsliu.iotapp.biz.utils.token.TokenGenerator;
import com.hinsliu.iotapp.dal.common.UserInfoDao;
import com.hinsliu.iotapp.domain.ErrorCodeEnum;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.exception.BusinessException;
import com.hinsliu.iotapp.domain.model.UserInfoDO;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.query.UserRegisterQuery;
import com.hinsliu.iotapp.domain.view.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: manager for user login and register
 * @author: liuxuanming
 * @date: 2021/03/25 2:29 下午
 */
@Slf4j
@Service
public class UserInfoManager {

    @Resource
    private UserInfoDao userInfoDao;

    public UserInfoDTO login(UserLoginQuery query) {

        UserInfoDO user = userInfoDao.query(query);
        log.info("校验用户信息，找到用户 >> {}", user);
        // 找到用户，生成Token并返回
        if (user != null) {
            RedisUtil redis = RedisUtil.getInstance();
            String token = TokenGenerator.generate(user.getUserName(), user.getUserPassword());
            redis.set(token, user.getUserName());

            // 生成返回结果
            UserInfoDTO userDTO = new UserInfoDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "用户不存在或密码错误");
        }
    }

    public void register(UserRegisterQuery query) {
        UserInfoDO user = userInfoDao.queryByRegister(query);
        log.info("根据用户注册信息查询到用户 >> {}", user);
        if (user == null) {
            user = new UserInfoDO();
            user.setUserName(query.getUserName());
            user.setUserPassword(query.getUserPassword());
            user.setEmail(query.getEmail());

            int affectCount = userInfoDao.insert(user);
            if (affectCount > 0) {
                log.info("新注册用户{} >> {}", user.getId(), affectCount);
            } else {
                throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "用户注册失败");
            }
        } else {
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "用户名已经存在");
        }
    }
}
