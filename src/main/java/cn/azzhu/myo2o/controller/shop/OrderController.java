package cn.azzhu.myo2o.controller.shop;

import cn.azzhu.myo2o.service.ProductService;
import cn.azzhu.myo2o.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author azzhu
 * @create 2019-08-26 13:07:09
 */
@Controller
@RequestMapping("/shop/order")
public class OrderController {

    @Autowired
    private ProductService productService;

    /**
     * 跳转到商品列表页面
     * @return
     */
    @GetMapping("/list.html")
    public String toList() {
        return "shop/order/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object getList() {
        Map<String,Object> map = new HashMap<>();
        productService.getPage(1,10,new ProductVo());
        return map;
    }
}
