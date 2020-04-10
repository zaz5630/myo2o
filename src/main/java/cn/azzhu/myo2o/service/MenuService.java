package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理的服务接口
 * @author azzhu
 * @create 2019-09-03 23:08:09
 */
public interface MenuService {
    /**
     * 查询所有菜单【带查询条件】
     * List<Menu>
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Long userId);

    /**
     * 查询所有菜单
     * @param menuVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    DataGridView queryAllMenu(Integer pageNum,Integer pageSize,MenuVo menuVo);

    /**
     * 添加菜单
     * @param menuVo
     */
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(Integer pid);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);
}
