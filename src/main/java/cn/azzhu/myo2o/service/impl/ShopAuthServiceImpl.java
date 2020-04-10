package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.entity.ShopAuthMap;
import cn.azzhu.myo2o.mapper.shop.ShopAuthMapMapper;
import cn.azzhu.myo2o.service.ShopAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-01 12:39:18
 */
@Service
public class ShopAuthServiceImpl implements ShopAuthService {
    @Autowired
    private ShopAuthMapMapper shopAuthMapMapper;
    @Override
    public List<ShopAuthMap> getShopsByAuthId(LocalAuth auth) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id",auth.getUserId());
        return shopAuthMapMapper.selectList(queryWrapper);
    }
}
