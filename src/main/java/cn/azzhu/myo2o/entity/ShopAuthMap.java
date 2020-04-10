package cn.azzhu.myo2o.entity;

import lombok.Data;

import java.util.Date;

/**
 *  店铺---用户映射表
 */
@Data
public class ShopAuthMap {
    private Integer shopAuthId;

    private Long employeeId;

    private Long shopId;

    private String name;

    private String title;

    private Integer titleFlag;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

}