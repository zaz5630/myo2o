package cn.azzhu.myo2o;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @author azzhu
 * @create 2020-03-24 15:11:22
 */
public class TestQiNiu {

    @Test
    public void test() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "V2tDY0oLMGJX1y0pz6H2KTClK-CuTMOKuslZI5wN";
        String secretKey = "k6chBn_l1PbQUbL-qFxHi9_5yoOpih0uMgpWzkJF";
        String bucket = "zaztest";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //    String localFilePath = "/home/qiniu/test.png";
        String localFilePath = "F:\\桌面\\1.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);

            String fileName = putRet.key;
            String domainOfBucket = "zaztest.s3-cn-east-1.qiniucs.com";
            String finalUrl = String.format("%s/%s", domainOfBucket, fileName);
            System.out.println(finalUrl);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }
}
