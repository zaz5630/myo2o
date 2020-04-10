package cn.azzhu.myo2o.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author azzhu
 * @create 2019-08-25 22:22:19
 */
@Controller
@RequestMapping("/shop/op")
public class ShopOperationController {

    /**
     * 跳转到店铺注册页面
     * @return
     */
    @GetMapping("/register.html")
    public String toRegister() {
        return "shop/info/register.html";
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    public String register(@RequestParam("userName") String userName,
                           @RequestParam("password") String password) {
        //1.获取用户名和密码

        //2.保存
        return "redirect:shop/op/list";
    }

    /**
     * 校验用户名是否村存在
     * @param userName
     * @return
     */
    @GetMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(@RequestParam("userName") String userName) {

        return "ok";
    }

    @GetMapping("/storeList.html")
    public String getStoreList() {
        return "shop/info/list";
    }
}
