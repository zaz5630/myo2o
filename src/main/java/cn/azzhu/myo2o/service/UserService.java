package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.UserVo;

/**
 * @author azzhu
 * @create 2019-09-08 11:07:19
 */
public interface UserService{
    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户【操作2张表】
     * @param userVo
     */
    void addUser(UserVo userVo);

    /**
     * 修改用户【操作2张表】
     * @param userVo
     */
    void updateUser(UserVo userVo);

    /**
     * 根据id删除用户【操作2张表】
     * @param userid
     */
    void deleteUser(Long userid);

    /**
     * 批量删除用户【操作2张表】
     * @param ids
     */
    void deleteBatchUser(Long[] ids);

    /**
     * 重置密码 操作一张表
     * @param userid
     */
    void resetUserPwd(Long userid);

    /**
     * 加载用户管理的分配角色的数据
     * @param userId
     * @return
     */
    DataGridView queryUserRole(Long userId);

    /**
     *  保存用户和角色的关系
     * @param userVo
     */
    void saveUserRole(UserVo userVo);
}
