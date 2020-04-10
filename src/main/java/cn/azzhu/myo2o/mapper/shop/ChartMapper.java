package cn.azzhu.myo2o.mapper.shop;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @author azzhu
 * @create 2019-09-05 11:53:05
 */
@Repository
public interface ChartMapper {

    /**
     * 商品热销top5
     * @param map
     * @return
     */
    List<Map<String,Object>> getTop5Product(Map<String,String> map);

    /**
     * 获取订单金额
     * @param map
     * @return
     */
    List<Map<String,Object>> getMoney(Map<String,String> map);

    /**
     * 订单状态分布分析
     * @param map
     * @return
     */
    Map<String,Integer> getOrderStatus(Map<String,String> map);
}
