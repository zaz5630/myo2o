package cn.azzhu.myo2o.dto;

import lombok.Data;

/**
 * 购物车
 * @author azzhu
 * @create 2019-08-19 21:39:02
 */
@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
