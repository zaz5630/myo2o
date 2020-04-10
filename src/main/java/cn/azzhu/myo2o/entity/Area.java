package cn.azzhu.myo2o.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Area {
  private Long areaId;
  private String areaName;
  private String areaDesc;
  private Integer priority;
  private Date createTime;
  private Date lastEditTime;
}