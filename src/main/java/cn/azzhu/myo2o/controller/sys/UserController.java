package cn.azzhu.myo2o.controller.sys;

import cn.azzhu.myo2o.service.UserService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.utils.ResultObj;
import cn.azzhu.myo2o.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author azzhu
 * @create 2019-09-07 17:16:42
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 加载用户列表返回DataGridView
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return this.userService.queryAllUser(userVo);
    }

    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            this.userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            this.userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo) {
        try {
            this.userService.deleteUser(userVo.getUserId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo) {
        try {
            this.userService.deleteBatchUser(userVo.getUids());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo) {
        try {
            this.userService.resetUserPwd(userVo.getUserId());
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色的数据
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo) {
        return this.userService.queryUserRole(userVo.getUserId());
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo) {
        try {
            this.userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
