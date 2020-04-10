package cn.azzhu.myo2o.enums;

import lombok.Getter;

/**
 * @author azzhu
 * @create 2019-08-19 20:11:35
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
