package cn.azzhu.myo2o.controller.sys;

import cn.azzhu.myo2o.service.RoleService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.utils.ResultObj;
import cn.azzhu.myo2o.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理控制器
 * @author azzhu
 * @create 2019-09-06 23:16:21
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    /**
     * 加载角色列表返回DataGridView
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit,
                                    RoleVo roleVo) {
        return this.roleService.queryAllRole(page,limit,roleVo);
    }


    /**
     * 添加角色
     */
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo) {
        try {
            this.roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo) {
        try {
            this.roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除角色
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(RoleVo roleVo) {
        try {
            this.roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo) {
        try {
            this.roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     */
    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        return this.roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {

            this.roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;

        }
    }
}
