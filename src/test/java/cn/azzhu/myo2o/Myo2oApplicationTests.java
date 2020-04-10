package cn.azzhu.myo2o;

import com.mysql.jdbc.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myo2oApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Driver.class);
    }

}
