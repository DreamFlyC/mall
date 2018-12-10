package com.fun.mall.service.impl;

import com.fun.mall.common.Const;
import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.User;
import com.fun.mall.persistence.UserMapper;
import com.fun.mall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 11:50 2018/11/26
 * @ Description：用户接口实现
 * @ Modified By：
 * @Version: 1.0$
 */
@Transactional(rollbackFor = Exception.class)
@Service(value = "UserServiceImpl")
public class UserServiceImpl implements IUserService {

    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /*
     * create by: CZP
     * description:登录功能
     * create time: 17:36 2018/11/30
     * @return 
     */
    @Override
    public ServerResponse<User> login(String phone, String password) {
        int resultCount=userMapper.checkPhone(phone);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("手机号码不存在");
        }
      //  String md5Password= MD5Util.MD5EncodeUtf8(password);
        User user=userMapper.selectLogin(phone,password);
        if(user==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }
    
    /*
     * create by: CZP
     * description:注册功能
     * create time: 17:36 2018/11/30
     * @return 
     */
    @Override
    public ServerResponse<User> register(User user){
        ServerResponse validResponse=null;
        if(StringUtils.isNotBlank(user.getUsername())){
            validResponse=this.checkValid(user.getUsername(), Const.USERNAME);
            if(!validResponse.isSuccess()){
                return validResponse;
            }
        }
        if(StringUtils.isNotBlank(user.getEmail())){
            validResponse=this.checkValid(user.getEmail(),Const.EMAIL);
            if(!validResponse.isSuccess()){
                return validResponse;
            }
        }
        if(StringUtils.isNotBlank(user.getPhone())) {
            validResponse = this.checkValid(user.getPhone(), Const.PHONE);
            if (!validResponse.isSuccess()) {
                return validResponse;
            }
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        int resultCount=userMapper.insertSelective(user);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }
    
    /*
     * create by: CZP
     * description:验证信息功能
     * create time: 11:52 2018/12/1
     * @return 
     */
    @Override
    public ServerResponse checkValid(String str, String type) {
        if(StringUtils.isNotBlank(str)){
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUserName(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount=userMapper.checkEmail(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount=userMapper.checkPhone(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("手机号码已存在");
                }
            }
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /*
     * create by: CZP
     * description:获取用户信息功能
     * create time: 11:53 2018/12/1
     * @return 
     */
    @Override
    public ServerResponse getUserInfo(User user){
        User userInfo=userMapper.getUserInfo(user.getId());
        if(userInfo==null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccess(userInfo);
    }

    /*
     * create by: CZP
     * description:忘记密码找回功能
     * create time: 12:08 2018/12/1
     * @return
     */
    @Override
    public ServerResponse forgetQuestion(String username){
        String question=userMapper.getQuestion(username);
        if(StringUtils.isBlank(question)){
            return ServerResponse.createBySuccessMessage("该用户未设置找回密码问题");
        }
        return ServerResponse.createBySuccess("下面是问题",question);
    }
    
    /*
     * create by: CZP
     * description:提交问题答案
     * create time: 14:12 2018/12/1
     * @return 
     */
    @Override
    public  ServerResponse forgetCheckAnswer(String username, String question, String answer){
        int resultCount=userMapper.forgetCheckAnswer(username,question,answer);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("问题答案错误");
        }
        String token = UUID.randomUUID().toString();
        return ServerResponse.createBySuccess(token);
    }
}
