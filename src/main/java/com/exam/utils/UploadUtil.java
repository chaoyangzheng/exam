package com.exam.utils;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
//          String imgUploadURL = serverPath + imgName;
        return imgUploadURL;
    }


public String executeUpload1(String uploadDir,MultipartFile file,String fileName) throws Exception{
        //文件后缀名
    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

    //上传文件名
    String filename = fileName + suffix;

    //服务器端保存的
    File serverFile = new File(uploadDir + filename);

    //将上传的文件写入到服务器端文件内
    file.transferTo(serverFile);

    return  filename;

}


//    public <T>List<T> importExecl(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
//        if(StringUtils.isBlank(filePath)){
//            return null;
//        }
//        ImportParams params =new ImportParam();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        List<T> list = null;
//        try {
//            list = ExeclImportUtil.importExecl(new File(filePath),pojoClass,params);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    return list;
//
//    }


}
