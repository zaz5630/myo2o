package cn.azzhu.myo2o.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转控制器    作用：路由
 * @author azzhu
 * @create 2019-09-06 19:59:54
 */
@Controller
@RequestMapping("sys")
public class SysController {
    /**
     * 跳转菜单管理
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转菜单管理左边的的菜单树页面
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }
    /**
     * 跳转菜单管理右边的菜单列表
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转角色管理页面
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转日志管理页面
     */
    @RequestMapping(value = "toLogInfoManager")
    public String toLogInfoManager() {
        return "system/logInfo/logInfoManager";
    }

    /**
     * 跳转公告管理页面
     */
    @RequestMapping("toNewsManager")
    public String toNewsManager() {
        return "system/news/newsManager";
    }

    /**
     * 跳转用户管理页面
     */
    @RequestMapping("toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }
}
