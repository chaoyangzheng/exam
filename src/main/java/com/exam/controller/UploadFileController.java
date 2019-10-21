package com.exam.controller;


import com.exam.common.JsonResult;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    public JsonResult uploadExcel(MultipartFile file){
        //判断是否为空
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        //获取MultipartFile 文件名
        String mFileName = file.getOriginalFilename();
        //获取文件后缀名
        String endName  = mFileName.substring(mFileName.lastIndexOf(".")+1);

        if(!endName.endsWith("xlsx") && !endName.endsWith("xls")){
            return new JsonResult(1,"请传入正确的Excel格式文件！",null,null);
            System.out.println("请传入正确的Excel格式文件！");
        }
        try {
            //poi需要的是File格式的输入流，需要先将MultipartFile转为File
            //上传文件存放到本地
            Date now = new Date();
            String fileName = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
            String dateStr = sdf.format(now);
            String path = dateStr;// request.getSourceUrl();
            logger.info("save path:" + path);
            //获得MultipartFile的输入流
            InputStream in = file.getInputStream();
            byte[] bytes = new byte[in.available()];
            // 将MultipartFile 文件中的内容读入到数组中
            in.read(bytes);
            // 文件保存位置
            File saveDir = new File(path);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File savefile = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(savefile);
            //将MultipartFile的输入流写入新创建的File文件中
            fos.write(bytes);
            if (fos != null) {
                fos.close();
            }
            in.close();
            FileInputStream stream = new FileInputStream(savefile);
            //获得execl文件输入流
            InputStream inputStream = file.getInputStream();
            //获得Execl文件
            Workbook wb = WorkbookFactory.create(stream);
            //获取第一个工作表
            Sheet readSheet = wb.getSheetAt(0);
            遍历工作表
            for (Row row : readSheet) {
                //省略execl第一行，第一行一般为格列的标题
                if(row.getRowNum()==0){
                    continue;
                }
                //获取文本
                String  = row.getCell(4).getStringCellValue();
                //获取数字类型的列
                double money = row.getCell(10).getNumericCellValue();
                BigDecimal totalAchievement = new BigDecimal(money);
                //获取时间类型的列，需要转换
                double time = row.getCell(6).getNumericCellValue();
                Date date = DateUtil.getJavaDate(time);
            }

        }catch (Exception e){

        }
    return  null;
    }
















///**
// * 处理Excel文件解析的方法
// * @param  file 前台上传的文件的对象
// * @return
// */
//@RequestMapping(value = "/excelparser")
//    @ResponseBody
//    public Map<String,Object> Excel(HttpServletRequest request, @RequestParam("file")MultipartFile file) throws Exception{
//
//        Map<String,Object> dataMap = new HashMap<String, Object>();
//
//        //设置文件名，根据业务需要替换成要下载的文件名
//        String fileName1 = request.getParameter("fileName");
//
//        String fileName;
//        try{
//            //上传目录地址,getRealPath返回绝对路径，对于最后的一个upload/进行处理，最后一个/改为\\
//            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
//
//            //修改最后一个upload/中的最后一个/符号修改为\\
//            uploadDir = uploadDir.substring(0,uploadDir.length()-1);
//            //最后的下载目录
//            uploadDir = uploadDir+"\\";
//
//            String realPath = uploadDir+fileName1;
//
//            File dir = new File(realPath);
//
//            if(!dir.exists()){
//                dir.mkdir();
//            }
//
//            //调用上传方法
//            fileName = upload.executeUpload1(uploadDir,file,fileName1);
//            uploadDir = uploadDir.substring(0,uploadDir.length()-1);
//            dataMap.put("fileName",fileName);
//            dataMap.put("dir",uploadDir);
//        }catch (Exception e){
//            //打印错误信息
//            e.printStackTrace();
//            return  api.returnJson(1,"解析失败",dataMap);
//        }ExcelParser(fileName);
//        return api.returnJson(0,"解析成功",dataMap);
//    }
//
//    public void ExcelParser(String fileName)throws Exception{
//
//    //ExcellmportUtil.importExcelMore解析Excel的一个关于空文本列的行，是否有必要读取，或者能不能进行过滤。
//        ImportParams params = new ImportParams();
//        params.setTitleRows(1);
//        params.setHeadRows(1);
//        long start = new Date().getTime();
//        List<Questions> list = new ArrayList<>();
//        list = upload.importExcel("/"+fileName,1,1, Layer.class);
//
//        System.out.println(new Date().getTime() -start);
//        System.out.println(list.size());
//        System.out.println(list);
//
//        int testId = 1;
//        int isInsert = 1;
//        for(int i = 0;i < list.size();i++){
//            Questions questions = new Questions();
//            UUID uuid = UUID.randomUUID();
//            String layerId = uuid.toString();
//            questions.setQuestionsId(layerId);
//            questions.setQuestionsSubjectId(list.get(i).getQuestionsSubjectId());
//            questions.setQuestionsTypeId(list.get(i).getQuestionsTypeId());
//            questions.setQuestionsInfo(list.get(i).getQuestionsInfo());
//            questions.setQuestionsAnswer(list.get(i).getQuestionsAnswer());
//
//            questions.setUploadTime(new Date());
//            questionsService.insertQuestions(questions);
//        }
//    }





}
