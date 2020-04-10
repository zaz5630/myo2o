package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.PersonInfo;

/**
 * @author azzhu
 * @create 2019-08-28 13:45:39
 */
public interface PersonInfoService {

    /**
     * 添加用户
     * @param personInfo
     * @return
     */
    int addPersonInfo(PersonInfo personInfo);

    /**
     * 根据id查询PersonInfo信息
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);
}
