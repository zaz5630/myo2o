package cn.azzhu.myo2o;

import cn.azzhu.myo2o.entity.User;
import cn.azzhu.myo2o.mapper.User2Mapper;
import cn.azzhu.myo2o.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.repository.query.ExampleQueryMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private User2Mapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        System.out.println(("----- insert method test ------"));
        User user = new User();
        //user.setName("Billie2");
        user.setAge(30);
        user.setEmail("aaaa@11.com");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate() {
        System.out.println(("----- update method test ------"));
        User user = new User();
        user.setId(2L);
        user.setName("Billie444");
        userMapper.updateById(user);
    }

    @Test
    public void testDel() {
        System.out.println(("----- Del method test ------"));
        User user = new User();
        user.setId(1L);
        //user.setName("Billie444");
        userMapper.deleteById(1L);
    }

    @Test
    public void testBetweenAnd() {
        System.out.println(("----- BetweenAnd ------"));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //支持链式调用
        //wrapper.between("id",1,3).gt("id",2);
        //wrapper.isNotNull("id").ge("id",1);
      //  userMapper.selectList(wrapper).forEach(System.out::println);
        //使用lambda表达式去构建查询条件
       // System.out.println(userMapper.selectOne(wrapper.lambda().eq(User::getId, 1)));
        userMapper.selectList(wrapper.lambda().between(User::getId, 1,3))
        .forEach(System.out::println);
    }

    @Test
    public void testDML() {
        System.out.println(("----- DML with conditions ------"));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        User user = new User();
        user.setName("aaaa");
        //添加条件
        updateWrapper.in("id",1,2);

        userMapper.update(user,updateWrapper);
    }

    @Test
    //测试乐观锁的使用
    //UPDATE tb_user SET name='ssssss', u_time='2020-03-19 14:14:51.531', version=3 WHERE id=1 AND version=2 AND deleted=1
    public void testOptimisticLocker() {
        int id = 100;
        int version = 1;    //oldversion

        User u = new User();
        u.setId(2L);
        u.setVersion(version);
        u.setName("ssssss");

        if(userMapper.updateById(u) > 0){
            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }
    }
}
