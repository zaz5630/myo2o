package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.mapper.shop.LocalAuthMapper;
import cn.azzhu.myo2o.service.LocalAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author azzhu
 * @create 2019-08-26 20:10:29
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Override
    public LocalAuth checkUserName(String userName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",userName);
        LocalAuth localAuth = localAuthMapper.selectOne(wrapper);
        return localAuth;
    }

    @Override
    public LocalAuth login(String userName, String pwd) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",userName);
        wrapper.eq("password",pwd);
        return localAuthMapper.selectOne(wrapper);
    }

    @Override
    public int addLocalAuth(LocalAuth localAuth) {
        return localAuthMapper.insert(localAuth);
    }
}
