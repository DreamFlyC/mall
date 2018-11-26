package com.fun.mall.service.impl;

import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.User;
import com.fun.mall.persistence.UserMapper;
import com.fun.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 11:50 2018/11/26
 * @ Description：用户接口实现
 * @ Modified By：
 * @Version: 1.0$
 */
@Service(value = "iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {

        return null;
    }
}
