package cn.azzhu.myo2o.utils;

import lombok.Data;

/**
 * @author azzhu
 * @create 2019-09-01 19:47:10
 */
@Data
public class ResultObj {
    private Integer code;
    private String msg;

    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstants.CODE_FAILER, SysConstants.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.DELETE_ERROR);

    //添加成功
    public static final ResultObj ADD_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.ADD_SUCCESS);

    //添加失败
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstants.CODE_FAILER, SysConstants.ADD_ERROR);
    /*下架*/
    public static final ResultObj DOWN_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.DOWN_ERROR);
    public static final ResultObj DOWN_SUCCESS=new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.DOWN_SUCCESS);

    /*上架*/
    public static final ResultObj UP_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.UP_ERROR);
    public static final ResultObj UP_SUCCESS=new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.UP_SUCCESS);

    /*修改*/
    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.UPDATE_ERROR);
    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.UPDATE_SUCCESS);

    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS=new ResultObj(SysConstants.CODE_SUCCESS, SysConstants.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR=new ResultObj(SysConstants.CODE_FAILER, SysConstants.DISPATCH_ERROR);

    /**
     * 状态码0
     */
    public static final ResultObj STATUS_TRUE=new ResultObj(SysConstants.CODE_SUCCESS);
    /**
     * 状态码-1
     */
    public static final ResultObj STATUS_FALSE=new ResultObj(SysConstants.CODE_FAILER);
    private ResultObj(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    private ResultObj(Integer code) {
        super();
        this.code = code;
    }
}
