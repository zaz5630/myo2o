package cn.azzhu.myo2o.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private String orderId;

    private Long buyerId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

}