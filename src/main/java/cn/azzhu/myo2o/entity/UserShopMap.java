package cn.azzhu.myo2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * 店主--用户表
 */
@Data
public class UserShopMap {
    private Integer userShopId;

    private Integer userId;

    private Integer shopId;

    private String userName;

    private String shopName;

    private Date createTime;

    private Integer point;
}