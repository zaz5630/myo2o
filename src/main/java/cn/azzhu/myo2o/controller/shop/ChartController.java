package cn.azzhu.myo2o.controller.shop;

import cn.azzhu.myo2o.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azzhu
 * @create 2019-08-26 18:56:14
 */
@Controller
@RequestMapping("/shop/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/top5.html")
    public String toTop5() {
        return "shop/chart/top5";
    }

    @GetMapping("/order.html")
    public String toOrder() {
        return "shop/chart/order";
    }

    @GetMapping("/money.html")
    public String toMoney() {
        return "shop/chart/money";
    }

    /**
     * 商品热销Top5
     * @param month
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("top5Product")
    @ResponseBody
    public Object getTop5Product(@RequestParam(value = "month",required = false) String month,
                                 @RequestParam(value = "startTime",required = false) String startTime,
                                 @RequestParam(value = "endTime",required = false) String endTime) {
        //从订单表中明细表中，按照商品id，汇总统计
        Map<String, String> map = new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        List<Map<String, Object>> top5Product = chartService.getTop5Product(map);
        return top5Product;
    }

    /**
     * 已支付订单金额统计
     * @param month
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("money")
    @ResponseBody
    public Object getMoney(@RequestParam(value = "month",required = false) String month,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "endTime",required = false) String endTime) {
        //从订单表中，按照日期，汇总统计
        Map<String, String> map = new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        List<Map<String, Object>> money = chartService.getMoney(map);
        return money;
    }

    /**
     * 订单状态分析
     * @param month
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("orderstatus")
    @ResponseBody
    public Object getOrderStatus(@RequestParam(value = "month",required = false) String month,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "endTime",required = false) String endTime) {
        //从订单表中，按照日期，汇总统计
        Map<String, String> map = new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        Map<String, Integer> orderStatus = chartService.getOrderStatus(map);
        return orderStatus;
    }
}
