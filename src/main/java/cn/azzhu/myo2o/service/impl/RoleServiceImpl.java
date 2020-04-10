package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.entity.Role;
import cn.azzhu.myo2o.mapper.sys.MenuMapper;
import cn.azzhu.myo2o.mapper.sys.RoleMapper;
import cn.azzhu.myo2o.service.RoleService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.utils.SysConstants;
import cn.azzhu.myo2o.utils.TreeNode;
import cn.azzhu.myo2o.vo.RoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-06 22:53:28
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleVo.getRolename())) {
            queryWrapper.like("rolename",roleVo.getRolename());
        }

        if(!StringUtils.isEmpty(roleVo.getRoledesc())) {
            queryWrapper.like("roledesc",roleVo.getRoledesc());
        }

        if(!StringUtils.isEmpty(roleVo.getAvailable())) {
            queryWrapper.eq("available",roleVo.getAvailable());
        }
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return null;
    }

    @Override
    public DataGridView queryAllRole(Integer pageNum,Integer pageSize,RoleVo roleVo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = this.queryAllRoleForList(roleVo);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        DataGridView<Role> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(roles);
        return dataGridView;
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insert(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateById(roleVo);
    }

    @Override
    public void deleteRole(Integer roleid) {
        //删除角色表的数据
        roleMapper.deleteById(roleid);
        //根据角色id删除tb_role_menu里面的数据
        roleMapper.deleteRoleMenuByRid(roleid);
        //根据角色id删除tb_role_user里面的数据
        roleMapper.deleteRoleUserByRid(roleid);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        roleMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstants.AVAILABLE_TRUE);
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        List<Menu> allMenu  = menuMapper.selectList(menuQueryWrapper);

        // 根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu  = menuMapper.queryMenuByRoleId(SysConstants.AVAILABLE_TRUE, roleid);
        List<TreeNode> data = new ArrayList<>();

        for (Menu m1 : allMenu) {
            String checkArr = SysConstants.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = SysConstants.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == SysConstants.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }
        DataGridView<TreeNode> dataGridView = new DataGridView<>();
        dataGridView.setData(data);
        return dataGridView;
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid=roleVo.getRoleid();
        Integer [] mids=roleVo.getIds();
        //根据rid删除tb_role_menu里面所有数据
        this.roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
