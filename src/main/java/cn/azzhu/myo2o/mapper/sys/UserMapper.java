package cn.azzhu.myo2o.mapper.sys;

import cn.azzhu.myo2o.vo.UserVo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-08 10:22:59
 */
@Repository
public interface UserMapper {

    /**
     * 查询所有用户
     * 应该在personInfo表中添加一个属性type，用于标识用户类型【普通用户、管理员】
     * @param userVo
     * @return
     */
    List<UserVo> queryAllUsers(UserVo userVo);

    /**
     * 保存用户和角色的关系
     * @param uid
     * @param rid
     */
    void insertUserRole(@Param("uid")Long uid, @Param("rid")Integer rid) ;
}
