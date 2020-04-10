package cn.azzhu.myo2o.controller.shop;

import cn.azzhu.myo2o.utils.AppFileUtils;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.utils.RandomUtils;
import cn.azzhu.myo2o.utils.SysConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author azzhu
 * @create 2019-09-03 08:28:10
 */
@Controller
@RequestMapping("shop/file")
public class FileController {

    /**
     *  文件异步上传
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public DataGridView uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
        // 文件上传的父目录
        String parentPath = AppFileUtils.PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造文件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        // 得到文件原名
        String oldName = mf.getOriginalFilename();
        // 根据文件原名得到新名
        String newName = RandomUtils.createFileNameUseTime(oldName,SysConstants.FILE_UPLOAD_TEMP);
        File dest = new File(dirFile, newName);
        mf.transferTo(dest);

        Map<String,Object> map=new HashMap<>();
        map.put("src", dirName+"/"+newName);
        ArrayList<Object> list = new ArrayList<>();
        list.add(map);
        DataGridView<Object> dataGridView = new DataGridView<>();
        dataGridView.setData(list);
        return dataGridView;
    }

    /**
     * 不下载只显示
     */
    @RequestMapping("downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
        return AppFileUtils.downloadFile(response, path, "");
    }
}
