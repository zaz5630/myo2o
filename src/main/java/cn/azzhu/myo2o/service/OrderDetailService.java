package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.OrderDetail;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-05 13:56:10
 */
public interface OrderDetailService {
    /**
     * 根据订单id查询 订单明细
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
