package cn.azzhu.myo2o.mapper.sys;

import cn.azzhu.myo2o.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-06 22:47:59
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色id删除tb_role_menu中的数据
     * @param roleId
     */
    void deleteRoleMenuByRid(Integer roleId);

    /**
     * 根据角色id删除tb_role_user中的数据
     * @param roleId
     */
    void deleteRoleUserByRid(Integer roleId);

    /**
     * 保存角色和菜单的关系 tb_role_menu
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 根据用户id删除sys_role_user里面的数据
     * @param userid
     */
    void deleteRoleUserByUid(Long userid);

    /**
     * 根据用户ID查询角色
     * @param available
     * @param userId
     * @return
     */
    List<Role> queryRoleByUid(@Param("available")Integer available, @Param("uid") Long userId);
}
