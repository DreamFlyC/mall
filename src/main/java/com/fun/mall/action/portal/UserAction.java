package com.fun.mall.action.portal;

import com.fun.mall.common.Const;
import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.User;
import com.fun.mall.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 10:36 2018/11/23
 * @ Description：用户模块
 * @ Modified By：
 * @Version: 1.0$
 */
@Controller(value = "UserAction")
@RequestMapping(value = "/manage/user")
public class UserAction {

    private IUserService userService;

    /*
     * create by: CZP
     * description:用户登录
     * create time: 11:21 2018/11/26
      * @Param: null
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response=userService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

}
