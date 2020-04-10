package cn.azzhu.myo2o.enums;

import lombok.Getter;

/**
 * @author azzhu
 * @create 2019-08-19 20:10:26
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
