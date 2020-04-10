package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.service.LocalAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import static org.junit.Assert.*;

/**
 * @author azzhu
 * @create 2019-08-26 20:14:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocalAuthServiceImplTest {
    @Autowired
    private LocalAuthService authService;

    @Test
    public void checkUserName() {
        System.out.println(authService.checkUserName("admin"));
    }

    @Test
    public void login() {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println("=============>" +authService.login("admin", password));
    }
}