package cn.azzhu.myo2o.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail {
    private String detailId;

    private String orderId;

    private Long productId;

    private Integer shopId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}