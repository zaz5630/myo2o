package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.Area;
import cn.azzhu.myo2o.exception.O2OException;
import cn.azzhu.myo2o.mapper.shop.AreaMapper;
import cn.azzhu.myo2o.service.AreaService;
import cn.azzhu.myo2o.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-07-16 23:03:34
 */
@Service
@Slf4j
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static String AREALISTKEY = "arealist";
    @Override
    public List<Area> getAreaList()  {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();

        if(!redisUtil.exists(key)) {
            //key不存在，从数据库中查找，然后放入redis
            areaList = areaMapper.queryAreas();
            try {
                String jsonStr = mapper.writeValueAsString(areaList);
                redisUtil.set(key,jsonStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new O2OException(e.getMessage());
            }
        } else {
            String jsonStr = redisUtil.get(key);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,Area.class);
            try {
                String obj = mapper.readValue(jsonStr, String.class);
                areaList = mapper.readValue(obj,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new O2OException(e.getMessage());
            }
        }
        return areaList;
    }
}
 
 
