package cn.azzhu.myo2o.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
  private Long productCategoryId;
  private Long shopId;
  private String productCategoryName;
  private String productCategoryDesc;
  private Integer priority;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date createTime;
  private Date lastEditTime;
}
 
 
