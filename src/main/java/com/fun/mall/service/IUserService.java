package com.fun.mall.service;

import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.User;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 11:49 2018/11/26
 * @ Description：用户接口
 * @ Modified By：
 * @Version: 1.0$
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);
}
