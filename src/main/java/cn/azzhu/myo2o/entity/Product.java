package cn.azzhu.myo2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class Product{
  @TableId(value = "product_id",type=IdType.AUTO)
  private Long productId;
  private String productName;
  private String productDesc;
  private String imgAddr;// 简略图
  private String normalPrice; //原价
  private String promotionPrice;  //现价【推广价格】
  private Integer priority; //权重，越大越靠前显示
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date createTime;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date lastEditTime;
  private Integer enableStatus; //0 下架  1.在架
  private Integer point;  //商品积分

  @TableField(exist = false)
  private List<ProductImg> productImgList;  //商品详情图列表
  private Long productCategoryId;  //商品类别
  private Long shopId;  //商品属于哪个店铺

}
 
 
