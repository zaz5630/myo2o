package cn.azzhu.myo2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    @TableId(value = "roleid",type = IdType.AUTO)
    private Integer roleid;

    private String rolename;

    private String roledesc;

    private Integer available;

}