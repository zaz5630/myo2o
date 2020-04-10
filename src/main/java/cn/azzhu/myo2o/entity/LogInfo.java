package cn.azzhu.myo2o.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class LogInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String loginname;

    private String loginip;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date logintime;

}