package cn.azzhu.myo2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PersonInfo {

  @TableId(value = "user_id",type = IdType.AUTO)
  private Long userId;
  private String name;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private Date birthday;
  private String gender;
  private String phone;
  private String email;
  private String profileImg;
  private Integer customerFlag;
  private Integer shopOwnerFlag;
  private Integer adminFlag;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private Date createTime;
  private Date lastEditTime;
  private Integer enableStatus;
}
 
 
