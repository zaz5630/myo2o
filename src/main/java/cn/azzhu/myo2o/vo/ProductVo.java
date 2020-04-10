package cn.azzhu.myo2o.vo;

import cn.azzhu.myo2o.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-01 11:40:30
 */
@Data
public class ProductVo extends Product {
    private String productName;     //商品名称
    private String startPrice;      //现价的最低价格
    private String endPrice;        //现价的最高价格
    private String startTime;       //商品创建的时间
    private String endTime;         //商品创建的时间

    private List<Long> shopIds; //查询条件，记录当前登录人有哪些shopId
}
