package cn.azzhu.myo2o.task;

import cn.azzhu.myo2o.utils.AppFileUtils;
import cn.azzhu.myo2o.utils.SysConstants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class RecyleTempFileTask {
	
	/**
	 * 每天晚上12点执行 0 0 0 * * ?
	 * 0 0/1 * * * ? 每隔1分钟
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void recyleTempFile() {
		File file=new File(AppFileUtils.PATH);
		deleteFile(file);
	}
	
	/**
	 * 删除图片
	 * @param file
	 */
	public void deleteFile(File file) {
		if(null!=file) {
			File[] listFiles = file.listFiles();
			if(null!=listFiles&&listFiles.length>0) {
				for (File f : listFiles) {
					if(f.isFile()) {
						if(f.getName().endsWith(SysConstants.FILE_UPLOAD_TEMP)) {
							f.delete();
						}
					}else {
						//如果是文件夹，再递归删除
						deleteFile(f);
					}
				}
			}
		}
	}

}