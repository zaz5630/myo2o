package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.Role;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.RoleVo;
import java.util.List;

/**
 * 角色管理的服务接口
 * @author azzhu
 * @create 2019-09-06 22:49:12
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @param roleVo
     */
    List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id查询用户的可用角色
     * @param roleVo
     * @param userId
     */
    List<Role> queryRoleByUserIdForList(RoleVo roleVo,Integer userId);

    /**
     * 查询所有角色[带查询条件的分页]
     * @param pageNum
     * @param pageSize
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(Integer pageNum,Integer pageSize,RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleid
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatchRole(Integer [] ids);

    /**
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存角色和菜单的关系
     * @param roleVo
     */
    void saveRoleMenu(RoleVo roleVo);
}
