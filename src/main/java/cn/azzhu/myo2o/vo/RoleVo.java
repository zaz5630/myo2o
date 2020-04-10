package cn.azzhu.myo2o.vo;

import cn.azzhu.myo2o.entity.Role;
import lombok.Data;

@Data
public class RoleVo extends Role {
	//接收多个角色id
	private Integer [] ids;
}
