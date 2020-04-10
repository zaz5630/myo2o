package cn.azzhu.myo2o.vo;

import java.util.Date;

import cn.azzhu.myo2o.entity.News;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class NewsVo extends News {
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	
	/**
	 * 时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	
	//接收多个id
	private Integer [] ids;

}
