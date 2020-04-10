package cn.azzhu.myo2o.vo;

import cn.azzhu.myo2o.entity.PersonInfo;
import lombok.Data;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-08 10:10:59
 */
@Data
public class UserVo extends PersonInfo {
    /*分页参数*/
    private Integer page;
    private Integer limit;
    private String userName;
    private String password;

    private String birthTime;   //出生日期 防止400错误

    private Long[] uids;    //用于批量删除

    private Integer[] rids; //接收多个角色id
}
