package cn.azzhu.myo2o.mapper.sys;

import cn.azzhu.myo2o.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-03 23:07:24
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色id查询菜单
     * @param available
     * @param rid
     * @return
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer available,@Param("rid") Integer rid);

    /**
     * 根据用户id查询菜单
     * @param available
     * @param uid
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available,@Param("uid") Long uid);

    /**
     * 根据菜单id删除tb_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid")Integer mid);
}
