package cn.azzhu.myo2o.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 */
@Data
public class TreeNode {

	private Integer id;
	@JsonProperty("parentId")
	private Integer pid;

	private String title;
	private String icon;
	private String href;
	private Boolean spread;
	private String target;
	private List<TreeNode> children = new ArrayList<>();

	// 复选树的必要属性
	private String checkArr="0";//选中就是1

	/**
	 * dtree的复选树使用
	 *
	 * @param id
	 * @param pid
	 * @param title
	 * @param spread
	 * @param checkArr
	 */
	public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
		this.checkArr = checkArr;
	}

	/**
	 * 首页左边导航树的构造器
	 * @param id
	 * @param pid
	 * @param title
	 * @param icon
	 * @param href
	 * @param spread
	 * @param target
	 */
	public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.icon = icon;
		this.href = href;
		this.spread = spread;
		this.target = target;
	}

	public TreeNode(){}
}