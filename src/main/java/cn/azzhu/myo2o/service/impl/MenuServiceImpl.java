package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.mapper.sys.MenuMapper;
import cn.azzhu.myo2o.service.MenuService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.MenuVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-03 23:12:02
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        //单表查询
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(menuVo.getTitle())) {
            queryWrapper.like("title",menuVo.getTitle());
        }
        if(!StringUtils.isEmpty(menuVo.getAvailable())) {
            queryWrapper.eq("available",menuVo.getAvailable());
        }

        if(!StringUtils.isEmpty(menuVo.getId())) {
            queryWrapper.eq("id",menuVo.getId()).or().eq("pid",menuVo.getId());
        }
        return menuMapper.selectList(queryWrapper);
    }

    //后期权限管理完成之后再修改
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Long userId) {
      //  QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
      //  return menuMapper.selectList(queryWrapper);
        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    @Override
    public DataGridView queryAllMenu(Integer pageNum,Integer pageSize,MenuVo menuVo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Menu> list = this.queryAllMenuForList(menuVo);
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        DataGridView<Menu> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(list);
        return dataGridView;
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        menuMapper.insert(menuVo);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        menuMapper.updateById(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer pid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        return menuMapper.selectCount(queryWrapper);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {

        //删除菜单表的数据
        this.menuMapper.deleteById(menuVo.getId());

        //根据菜单id删除tb_role_menu里面的数据
        this.menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }
}
