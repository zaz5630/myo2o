package cn.azzhu.myo2o.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    //@TableId
    private Long id;
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Integer deleted;

    @TableField(value = "c_time",fill = FieldFill.INSERT)
    private Date cTime; //创建时间
    @TableField(value = "u_time",fill = FieldFill.UPDATE)
    private Date uTime; //修改时间

    @Version
    private Integer version;
}
