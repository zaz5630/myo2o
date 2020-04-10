package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.dto.OrderDTO;
import com.github.pagehelper.PageInfo;

/**
 * @author azzhu
 * @create 2019-09-05 13:55:54
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个
     * @param orderId
     * @return
     */
    OrderDTO getOrderById(String orderId);

    /**
     * 根据买家的openid 查询分页订单列表
     * @param buyerOpenid
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<OrderDTO> findByBuyerOpenid(String buyerOpenid, Integer pageNum, Integer pageSize);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询所有订单列表【分页】
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<OrderDTO> findList(Integer pageNum, Integer pageSize);
}
