package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.LocalAuth;

/**
 * @author azzhu
 * @create 2019-08-26 20:09:24
 */
public interface LocalAuthService {
    /**
     * 校验用户名
     * @param userName
     * @return
     */
    LocalAuth checkUserName(String userName);

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return
     */
    LocalAuth login(String userName,String pwd);

    /**
     * 插入用户账户信息
     * @param localAuth
     * @return
     */
    int addLocalAuth(LocalAuth localAuth);
}
