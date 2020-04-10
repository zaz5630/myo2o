package cn.azzhu.myo2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HeadLine {
  private Long lineId;
  private String lineName;
  private String lineLink;
  private String lineImg;
  private Integer priority;
  //0.不可用  1.可用
  private Integer enableStatus;
  private Date createTime;
  private Date lastEditTime;
}
 
 
