package cn.azzhu.myo2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="菜单对象", description="菜单")
public class Menu {
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	private Integer pid;
	@ApiModelProperty(value = "标题")
	private String title;

	private String href;

	private Integer spread;

	private String target;

	private String icon;

	private Integer available;

	public Menu() {	}

	public Menu(Integer id, Integer pid, String title, String href, Integer spread, String target, String icon,
			Integer available) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.href = href;
		this.spread = spread;
		this.target = target;
		this.icon = icon;
		this.available = available;
	}
}