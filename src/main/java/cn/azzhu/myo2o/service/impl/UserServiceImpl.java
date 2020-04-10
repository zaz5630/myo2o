package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.entity.Role;
import cn.azzhu.myo2o.mapper.shop.LocalAuthMapper;
import cn.azzhu.myo2o.mapper.shop.PersonInfoMapper;
import cn.azzhu.myo2o.mapper.sys.RoleMapper;
import cn.azzhu.myo2o.mapper.sys.UserMapper;
import cn.azzhu.myo2o.service.UserService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.utils.SysConstants;
import cn.azzhu.myo2o.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author azzhu
 * @create 2019-09-08 11:10:46
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        //设置分页参数
        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        //查询
        List<UserVo> list = userMapper.queryAllUsers(userVo);
        //封装分页数据
        PageInfo<UserVo> pageInfo = new PageInfo<>(list);

        //封装表格数据
        DataGridView<UserVo> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(list);
        return dataGridView;
    }

    @Override
    public void addUser(UserVo userVo) {
        //向tb_person_info插入一条数据
        Date currentTime = new Date();
        userVo.setCreateTime(currentTime);
        userVo.setLastEditTime(currentTime);
        userVo.setCustomerFlag(0);
        userVo.setAdminFlag(0);
        userVo.setEnableStatus(1);
        try {
            userVo.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userVo.getBirthTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.personInfoMapper.insert(userVo);

        //获取user_id
        Long userId = userVo.getUserId();
        //构建LocalAuth
        LocalAuth localAuth = new LocalAuth();
        localAuth.setUserName(userVo.getUserName());
        //设置默认密码
        localAuth.setPassword(DigestUtils.md5DigestAsHex(SysConstants.USER_DEFAULT_PWD.getBytes()));
        localAuth.setUserId(userId);
        localAuth.setCreateTime(currentTime);
        localAuth.setLastEditTime(currentTime);

        this.localAuthMapper.insert(localAuth);
    }

    @Override
    public void updateUser(UserVo userVo) {
        userVo.setLastEditTime(new Date());
        this.personInfoMapper.updateById(userVo);
    }

    @Override
    public void deleteUser(Long userid) {
        //删除tb_local_auth表数据
        UpdateWrapper<LocalAuth> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userid);
        this.localAuthMapper.delete(updateWrapper);

        //删除tb_person_info表数据
        this.personInfoMapper.deleteById(userid);

        // 根据用户id删除tb_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);
    }

    @Override
    public void deleteBatchUser(Long[] ids) {
        //删除tb_local_auth表数据
        QueryWrapper<LocalAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",Arrays.asList(ids));
        List<LocalAuth> localAuths = this.localAuthMapper.selectList(queryWrapper);
        //获取主键id，构建集合
        ArrayList<Long> localAuthIds = new ArrayList<>();
        for (LocalAuth localAuth : localAuths) {
            localAuthIds.add(localAuth.getLocalAuthId());
        }
        this.localAuthMapper.deleteBatchIds(localAuthIds);

        //删除tb_person_info表数据
        this.personInfoMapper.deleteBatchIds(Arrays.asList(ids));

        // 根据用户id删除tb_role_user里面的数据
        for (Long id : ids) {
            this.roleMapper.deleteRoleUserByUid(id);
        }
    }

    @Override
    public void resetUserPwd(Long userid) {
        //修改tb_local_auth表
        LocalAuth localAuth = new LocalAuth();
        localAuth.setPassword(DigestUtils.md5DigestAsHex(SysConstants.USER_DEFAULT_PWD.getBytes()));
        localAuth.setLastEditTime(new Date());
        //更新
        UpdateWrapper<LocalAuth> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userid);
        this.localAuthMapper.update(localAuth,updateWrapper);
    }

    @Override
    public DataGridView queryUserRole(Long userId) {
        // 1.查询所有可用的角色
        Role role=new Role();
        role.setAvailable(SysConstants.AVAILABLE_TRUE);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        List<Role> allRole = this.roleMapper.selectList(queryWrapper);

        // 2.根据用户ID查询已拥有的角色
        List<Role> userRole = this.roleMapper.queryRoleByUid(SysConstants.AVAILABLE_TRUE, userId);

        List<Map<String,Object>> data=new ArrayList<>();
        for (Role r1 : allRole) {
            Boolean LAY_CHECKED=false;
            for (Role r2 : userRole) {
                if(r1.getRoleid()==r2.getRoleid()) {
                    LAY_CHECKED=true;
                }
            }
            Map<String, Object> map=new HashMap<String, Object>();

            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        DataGridView<Map<String, Object>> dataGridView = new DataGridView<>();
        dataGridView.setData(data);
        return dataGridView;
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        Long userId = userVo.getUserId();
        Integer[] roleIds = userVo.getRids();

        //根据用户id删除tb_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(userId);

        //保存关系
        if(roleIds!=null&& roleIds.length>0) {
            for (Integer rid : roleIds) {
                this.userMapper.insertUserRole(userId,rid);
            }
        }
    }
}
