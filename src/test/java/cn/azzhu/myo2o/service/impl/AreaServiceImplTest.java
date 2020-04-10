package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author azzhu
 * @create 2019-08-31 20:59:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AreaServiceImplTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void getAreaList() {
        System.out.println(areaService.getAreaList());
    }
}