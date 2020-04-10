package cn.azzhu.myo2o;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JasyptTest {
    
    //注入StringEncryptor
    @Autowired
    StringEncryptor encryptor;
    
    @Test
    public void encry() {
        //加密root
        String username = encryptor.encrypt("root");
        System.out.println(username);
        //加密123
        String password = encryptor.encrypt("root");
        System.out.println(password);
    }

    @Test
    public void getPwd() {
        System.out.println("密码："+DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}