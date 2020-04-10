package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.PersonInfo;
import cn.azzhu.myo2o.mapper.shop.PersonInfoMapper;
import cn.azzhu.myo2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author azzhu
 * @create 2019-08-28 13:46:25
 */
@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Override
    public int addPersonInfo(PersonInfo personInfo) {
        return personInfoMapper.insert(personInfo);
    }

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoMapper.selectById(userId);
    }
}
