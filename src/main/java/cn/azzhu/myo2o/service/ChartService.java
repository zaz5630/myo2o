package cn.azzhu.myo2o.service;

import java.util.List;
import java.util.Map;

/**
 * 统计图相关接口
 * @author azzhu
 * @create 2019-09-05 16:54:28
 */
public interface ChartService {
    /**
     * 获取商品热销top数据，按时间维度查询
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
