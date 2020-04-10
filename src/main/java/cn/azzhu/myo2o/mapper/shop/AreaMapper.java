package cn.azzhu.myo2o.mapper.shop;

import cn.azzhu.myo2o.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-07-16 22:46:13
 */
@Repository //抑制其他地方自动装配找不到【但不会影响调用】
public interface AreaMapper {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryAreas();
}
 
 
