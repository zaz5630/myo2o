package cn.azzhu.myo2o.mapper.shop;

import cn.azzhu.myo2o.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author azzhu
 * @create 2019-09-05 13:54:01
 */
@ResponseBody
public interface OrderMapper extends BaseMapper<Order> {
}
