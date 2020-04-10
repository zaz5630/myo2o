package cn.azzhu.myo2o.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回分页数据格式
 * @author azzhu
 * @create 2019-08-28 23:18:23
 */
@Data
public class DataGridView<T> {
    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private List<T> data = new ArrayList<>();
}
