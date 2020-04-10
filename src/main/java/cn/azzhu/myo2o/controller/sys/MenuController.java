package cn.azzhu.myo2o.controller.sys;

import cn.azzhu.myo2o.annotation.Access;
import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.service.MenuService;
import cn.azzhu.myo2o.utils.*;
import cn.azzhu.myo2o.vo.MenuVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理控制器
 * @author azzhu
 * @create 2019-09-06 19:32:10
 */
@Api(description="菜单管理")
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "加载左侧菜单")
    @RequestMapping("loadIndexLeftMenuJson")
    @Access(value = {"admin","other"})  //配置注解权限, 允许身份为admin, 或者说允许权限为admin的人访问
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo, HttpSession session) {
        //得到当前登录的用户对象
        LocalAuth localAuth = (LocalAuth) session.getAttribute(SysConstants.SESSION_USER);
        List<Menu> list=null;
        menuVo.setAvailable(SysConstants.AVAILABLE_TRUE);    //只查询可用的
        if(localAuth.getUserName().equals("admin")) {   //此处应该根据SysContants中定义的用户类型查询
            list = menuService.queryAllMenuForList(menuVo);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            //应该先去缓存中取，若缓存中没有，再去数据库中取，取完之后要放入到缓存 menu:uid
            if(!redisTemplate.hasKey(SysConstants.MENU_KEY+localAuth.getUserId())) {
                //根据用户id查询
                list=this.menuService.queryMenuByUserIdForList(menuVo, localAuth.getUserId());
                //放入到redis中
                try {
                    String menuStr = mapper.writeValueAsString(list);
                    redisTemplate.opsForValue().set(SysConstants.MENU_KEY+localAuth.getUserId(),menuStr);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                //缓存中
                String menuVal = redisTemplate.opsForValue().get(SysConstants.MENU_KEY+localAuth.getUserId());
                System.out.println("hello===>" + menuVal);
                try {
                    //解析json串
                    list = mapper.readValue(menuVal,new TypeReference<List<Menu>>() { });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<TreeNode> nodes= new ArrayList<>();

        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()==SysConstants.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes,1);
    }

    /**
     * 加载菜单管理左边的菜单树
     */
    @PostMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstants.AVAILABLE_TRUE);//只查询可用的
        List<Menu> list=this.menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes= new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()==SysConstants.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        DataGridView<TreeNode> dataGridView = new DataGridView<>();
        dataGridView.setData(nodes);
        return dataGridView;
    }

    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit,
                                    MenuVo menuVo) {
        return this.menuService.queryAllMenu(page,limit,menuVo);
    }

    /**
     * 添加菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo) {
        try {
            menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo) {
        try {
            menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        Integer count=this.menuService.queryMenuByPid(menuVo.getId());
        if(count>0) {
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo) {
        try {
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
