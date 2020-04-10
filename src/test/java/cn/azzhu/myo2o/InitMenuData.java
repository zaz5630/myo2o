package cn.azzhu.myo2o;

import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.mapper.sys.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InitMenuData {

	@Autowired
	private MenuMapper menuMapper;

	@Test
	public void initMenuData() {

		menuMapper.insert(new Menu(1, 0, "店铺管理系统", null, 1, null, "&#xe68e;", 1));
		menuMapper.insert(new Menu(2, 1, "店铺信息管理", null, 1, null, "&#xe653;", 1));
		menuMapper.insert(new Menu(3, 1, "业务管理", null, 0, null, "&#xe663;", 1));
		menuMapper.insert(new Menu(4, 1, "系统管理", null, 0, null, "&#xe716;", 1));
		menuMapper.insert(new Menu(5, 1, "统计分析", null, 0, null, "&#xe629;", 1));
		
		
		menuMapper.insert(new Menu(6, 2, "注册店铺", null, 0, null, "&#xe770;", 1));
		menuMapper.insert(new Menu(7, 2, "门店列表", null, 0, null, "&#xe657;", 1));
		
		
		menuMapper.insert(new Menu(8, 3, "类别管理", null, 0, null, "&#xe65b;", 1));
		menuMapper.insert(new Menu(9, 3, "商品管理", null, 0, null, "&#xe6b2;", 1));
		menuMapper.insert(new Menu(10, 3, "订单管理", null, 0, null, "&#xe65a;", 1));

		
		menuMapper.insert(new Menu(12, 4, "菜单管理", null, 0, null, "&#xe60f;", 1));
		menuMapper.insert(new Menu(13, 4, "角色管理", null, 0, null, "&#xe66f;", 1));
		menuMapper.insert(new Menu(14, 4, "用户管理", null, 0, null, "&#xe770;", 1));
		menuMapper.insert(new Menu(15, 4, "日志管理", null, 0, null, "&#xe655;", 1));
		menuMapper.insert(new Menu(16, 4, "公告管理", null, 0, null, "&#xe645;", 1));
		menuMapper.insert(new Menu(17, 4, "数据源监控", null, 0, null, "&#xe857;", 1));
		
		
		menuMapper.insert(new Menu(18, 5, "购买力Top5", null, 0, null, "&#xe63c;", 1));
		menuMapper.insert(new Menu(19, 5, "热销商品", null, 0, null, "&#xe62c;", 1));
		menuMapper.insert(new Menu(20, 5, "订单分布", null, 0, null, "&#xe62d;", 1));
		menuMapper.insert(new Menu(21, 5, "交易额统计", null, 0, null, "&#xe62d;", 1));

		System.out.println("初始化完成");

	}
}