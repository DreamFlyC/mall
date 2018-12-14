package com.fun.mall.common;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 11:55 2018/11/26
 * @ Description：常量池
 * @ Modified By：
 * @Version: 1.0$
 */
public class Const {

    public static final String CURRENT_USER="SESSION_LOGIN";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE="phone";

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME=60*30;
    }

    public interface Role{
        // 普通用户
        int ROLE_CUSTOMER = 0;
        // 管理员
        int ROLE_ADMIN = 1;
    }
}
