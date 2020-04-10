package cn.azzhu.myo2o;

import org.springframework.util.DigestUtils;

/**
 * @author azzhu
 * @create 2020-03-19 14:47:34
 */
public class TestDigestUtils {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
