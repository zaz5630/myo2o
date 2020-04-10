package cn.azzhu.myo2o.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台
 * @author azzhu
 * @create 2019-08-26 19:06:59
 */
@Controller
@RequestMapping("/shop")
public class WorkBenchController {

    @GetMapping("/workbench")
    public String toWorkBench() {
        return "common/main";
    }
}
