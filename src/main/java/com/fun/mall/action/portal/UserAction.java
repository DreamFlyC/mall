package com.fun.mall.action.portal;

import com.fun.mall.common.Const;
import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.User;
import com.fun.mall.service.IUserService;
import com.fun.mall.util.JsonUtil;
import com.fun.mall.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 10:36 2018/11/23
 * @ Description：用户模块
 * @ Modified By：
 * @Version: 1.0$
 */
@Controller(value = "UserAction")
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserAction {

    private  static Logger log= LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private IUserService userService;

    /*
     * create by: CZP
     * description:用户登录、登出
     * create time: 11:21 2018/11/26
     * @Param: null
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpSession session){
        log.info("username:{},password:{}",phone,password);
        ServerResponse<User> response=userService.login(phone,password);
        if(response.isSuccess()){

            RedisPoolUtil.setex(session.getId(),Const.RedisCacheExtime.REDIS_SESSION_EXTIME, JsonUtil.objToString(response));

           // session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "/logout.do")
    @ResponseBody
    public ServerResponse<User> logOut(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    /*
     * create by: CZP
     * description:用户注册
     * create time: 9:47 2018/11/27
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> register(User user){
        return userService.register(user);
    }
    
    /*
     * create by: CZP
     * description:检查用户名是否有效
     * create time: 11:40 2018/12/1
     * @return 
     */
    @RequestMapping(value = "/check_valid.do")
    @ResponseBody
    public ServerResponse checkValid(String str,String type){
        return userService.checkValid(str,type);
    }

    /*
     * create by: CZP
     * description:.获取登录用户信息
     * create time: 11:49 2018/12/1
     * @return
     */
    @RequestMapping(value = "/get_user_info.do")
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户信息");
        }
        return userService.getUserInfo(user);
    }
    
    /*
     * create by: CZP
     * description:忘记密码  localhost:8080/user/forget_get_question.do?username=czp
     * create time: 12:04 2018/12/1
     * @return 
     */
    @RequestMapping(value = "/forget_get_question.do")
    @ResponseBody
    public ServerResponse forgetQuestion(String username,HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户未登录,无法进行当前操作");
        }
        if(StringUtils.isNotBlank(username)){
            return userService.forgetQuestion(username);
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }

    }

    /*
     * create by: CZP
     * description:提交问题答案
     * create time: 14:12 2018/12/1
     * @return
     */
    @RequestMapping(value = "/forget_check_answer.do")
    @ResponseBody
    public ServerResponse forgetCheckAnswer(@RequestParam(value = "username") String username,
     @RequestParam(value = "question") String question, @RequestParam(value = "answer") String answer){
        if(StringUtils.isBlank(username)||StringUtils.isBlank(question)||StringUtils.isBlank(answer)){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return userService.forgetCheckAnswer(username,question,answer);
    }
}
