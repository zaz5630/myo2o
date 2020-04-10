package cn.azzhu.myo2o.controller.superadmin;

import cn.azzhu.myo2o.entity.Area;
import cn.azzhu.myo2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-07-16 23:11:41
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/listareas")
    public ModelAndView listAreas() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Area> list = areaService.getAreaList();
            mv.addObject("rows", list);
            mv.addObject("total", list.size());

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("success", false);
            mv.addObject("errMsg", e.toString());
        }
        mv.setViewName("test.html");
        return mv;
    }
}
 
 
