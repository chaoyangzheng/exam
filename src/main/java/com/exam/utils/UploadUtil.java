package com.exam.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传 工具类
 *
 * 接收图片，返回地址
 * 接收video，返回地址 + videoTime
 *
 * @author yuanzhe
 * @date 2019-10-05
 */

public class UploadUtil {

    public String ImgUpload(MultipartFile file) {

//        String serverPath = "/usr/local/tomcat/webapps/show";
        String serverPath = "C:\\Users\\Administrator\\Desktop\\test";

        File file1 = new File(serverPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String imgName = uuid + filename;

        File file2 = new File(serverPath, imgName);

        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imgUploadURL = "http://15637237221.wicp.vip/show/" + imgName;

        return imgUploadURL;
    }
}
