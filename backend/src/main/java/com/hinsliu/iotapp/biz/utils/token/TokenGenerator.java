package com.hinsliu.iotapp.biz.utils.token;

import org.springframework.util.DigestUtils;

/**
 * @Description: Token generator for utils.
 * @author: liuxuanming
 * @date: 2021/03/30 10:59 上午
 */
public class TokenGenerator {

    public static String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for(String s : strings) {
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        return DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
    }

}
