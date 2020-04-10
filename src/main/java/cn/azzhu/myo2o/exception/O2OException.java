package cn.azzhu.myo2o.exception;

import cn.azzhu.myo2o.enums.ResultEnum;

/**
 * @author azzhu
 * @create 2019-08-26 20:05:15
 */
public class O2OException extends RuntimeException {
    private Integer code;

    public O2OException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public O2OException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public O2OException(String msg) {
        super(msg);
    }
}
