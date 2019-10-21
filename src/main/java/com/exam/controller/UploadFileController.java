package com.exam.controller;



import com.exam.common.JsonResult;

import com.exam.service.QuestionsService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;



@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    QuestionsService questionsService;


    @RequestMapping("/file")
    public JsonResult importSelectQuestions(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            new JsonResult(1,"",null,null);
        }
            InputStream inputStream = file.getInputStream();
            questionsService.insertQuestions(inputStream);
        return new JsonResult(0, "success",null,null);
    }



//    @ApiOperation(value = "导入excel到题库", notes = "1代表选择题，2代表判断题，其他代表简述题")
/*
    @PostMapping("/importQuestions.do")
    public JsonResult importSelectQuestions(MultipartFile file) {
        if (file.isEmpty()) {
            new JsonResult(1,"" ,null,codeMsg.getIsEmpty());
        }
        try {
            InputStream inputStream = file.getInputStream();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new JsonResult(0,"成功" ,null,codeMsg.getExecteSuccess());
    }
*/





 /*   *//**
     * @Author: qlq
     * @Description 导入课程信息(以Excel模板的形式导入)
     * @Date: 11:04 2018/5/5
     *//*

    *//**
     * 导入课程(以Excel的形式导入)
     * 1.导入文件，将文件保存到本地
     * 2.读取Excel提取课程信息
     * 3.进行数据库保存
     * 4.反馈导入信息
     *//*
    @Controller
    public class ImportCourseExcel {
        private Logger logger = Logger.getLogger(ImportCourseExcel.class);//日志记录器
        @Autowired
        private CourseBaseInfoService courseBaseInfoService;//课程service
        *//**
         * 导入课程信息(以课程信息导入)
         * @param file
         * @return
         *//*
        @RequestMapping("/uploadCourseExcel")
        public @ResponseBody
        ResposeResult uploadCourseExcel(MultipartFile file){
            ResposeResult resposeResult = new ResposeResult();
            String fileOriName = null;
            String fileNowName = null;
            if(file == null){
                resposeResult.setMsg("请上传正确的Excel文件");
                return resposeResult;
            }
            //1.保存文件到本地
            fileOriName = file.getOriginalFilename();//获取原名称
            fileNowName = UUIDUtil.getUUID2()+"."+ FilenameUtils.getExtension(fileOriName);//生成唯一的名字

            try {
                fileNowName  = FileHandleUtil.uploadSpringMVCFile(file, "courseExcelFileImport", fileNowName);//保存文件
            } catch (Exception e) {
                resposeResult.setMsg("请上传正确的Excel文件");
                logger.error("导入课程信息失败失败",e);
            }
            //2.读取文件
            String fileQualifyName = ResourcesUtil.getValue("path","courseExcelFileImport")+fileNowName;//生成文件全路径
            List<TCourseBaseInfo> tCourseBaseInfos = this.readExcelData(fileQualifyName);//读取的Excel数据
            if(tCourseBaseInfos == null || tCourseBaseInfos.size()==0){
                resposeResult.setMsg("您上传的文件没有课程信息，请重新编辑");
                return resposeResult;
            }
            //3.保存数据库
            List<String> repeatCourseNums = null;
            try {
                repeatCourseNums = courseBaseInfoService.addCourseBaseInfoBatch(tCourseBaseInfos);
            } catch (SQLException e) {
                resposeResult.setMsg("保存数据库的时候出错");
                logger.error("保存数据库出错");
            }
            //4.根据返回结果判断重复的数据与条数。
            int allTotal = tCourseBaseInfos.size();
            // 4.1如果重复的集合为空则证明全部上传成功
            if(repeatCourseNums == null || repeatCourseNums.size()==0){
                resposeResult.setMsg(allTotal+"条课程信息全部上传成功");
            }else {//4.2如果有重复提示哪些重复了
                int repeatSize = repeatCourseNums.size();
                resposeResult.setMsg("总共"+allTotal+"条数据，成功上传"+(allTotal - repeatSize)+"条,重复了"+repeatSize+"条。"+"重复的课程编号为"+repeatCourseNums.toString());
            }
            return resposeResult;
        }

        *//**
         * 读取Excel提取数据(返回提取的数据集合)
         * @param fileQualifyName
         * @return
         *//*
        private JsonResult readExcelData(String fileQualifyName) {
            List<Questions> datas = null;
            File file = new File(fileQualifyName);
            try {
//            获取一个工作簿
                HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
//            获取一个工作表两种方式
//            HSSFSheet sheet = workbook.getSheet("sheet0");
//            获取工作表的第二种方式
                HSSFSheet sheet = workbook.getSheetAt(0);
                int firstRow = 1;
//            获取sheet的最后一行
                int lastRow = sheet.getLastRowNum();
                if(lastRow <2){//如果只有1行或者0行就直接退出
                    return null;
                }
                datas =  new ArrayList<TCourseBaseInfo>();//用于返回的数据集合
                //循环内不要创建对象引用(集合中存的是对象的引用)
                TCourseBaseInfo courseBaseInfo = null;
                for(int i=firstRow;i<=lastRow;i++){
                    courseBaseInfo = new TCourseBaseInfo();
                    HSSFRow row = sheet.getRow(i);
                    int lastCol = row.getLastCellNum();
                    if(lastCol != 14){
                        //如果不是14列就不读这一行了。
                        continue;
                    }
                    for(int j=0;j<lastCol;j++){
                        HSSFCell cell= row.getCell(j);//获取一个cell
                        if (j == 0) {
                            courseBaseInfo.setCoursenum(cell.getStringCellValue());//课程编号
                            continue;
                        }
                        if (j == 1) {
                            courseBaseInfo.setCourseplatform(cell.getStringCellValue());//课程平台
                            continue;
                        }
                        if (j == 2) {
                            courseBaseInfo.setCoursenature(cell.getStringCellValue());//课程性质
                            continue;
                        }
                        if (j == 3) {
                            courseBaseInfo.setCoursenamecn(cell.getStringCellValue());//中文名称
                            continue;
                        }
                        if (j == 4) {
                            courseBaseInfo.setCoursenameen(cell.getStringCellValue());//英文名称
                            continue;
                        }
                        if (j == 5) {
                            courseBaseInfo.setCredit(cell.getStringCellValue());//学分
                            continue;
                        }
                        if (j == 6) {
                            courseBaseInfo.setCoursehour(cell.getStringCellValue());//学时
                            continue;
                        }
                        if (j == 7) {
                            courseBaseInfo.setTeachhour(cell.getStringCellValue());//讲课时长
                            continue;
                        }
                        if (j == 8) {
                            courseBaseInfo.setExperimenthour(cell.getStringCellValue());//实验时长
                            continue;
                        }
                        if (j == 9) {
                            courseBaseInfo.setComputerhour(cell.getStringCellValue());//上机时长
                            continue;
                        }
                        if (j == 10) {
                            courseBaseInfo.setPracticehour(cell.getStringCellValue());//实践时长
                            continue;
                        }
                        if (j == 11) {
                            courseBaseInfo.setWeeklyhour(cell.getStringCellValue());//周学时分配
                            continue;
                        }
                        if (j == 12) {
                            courseBaseInfo.setScoringway(cell.getStringCellValue());//计分方式
                            continue;
                        }
                        if (j == 13) {
                            courseBaseInfo.setCoursehourmethod(cell.getStringCellValue());//学时单位
                            continue;
                        }
                    }
                    //读完一行将数据塞进去
                    datas.add(courseBaseInfo);
                }
            } catch (IOException e) {
                logger.error("读取上传的Excel出错");
            }
            return datas;
        }
    }*/




/*    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> upload( MultipartFile file, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        List<Questions> mapPointList = new ArrayList<>();
        //读取IO流文件
        InputStream input = null;
        XSSFWorkbook wb = null;
        try {
            input=file.getInputStream();
            wb=new XSSFWorkbook(input);
            //读取页
            for(int sheetNum=0;sheetNum<wb.getNumberOfSheets();sheetNum++){
                XSSFSheet xssfSheet = wb.getSheetAt(sheetNum);
                if(xssfSheet==null){
                    continue;
                }
                //读取行
                for(int rowNum=1;rowNum<xssfSheet.getLastRowNum()+1;rowNum++){
                    Questions mapPoint = new Questions();
                    XSSFRow row= xssfSheet.getRow(rowNum);
                    if(row!=null){
                        for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
                            if(cellNum == 0){
                                mapPoint.setQuestionsSubjectId(row.);
                            }
                            row.getCell(cellNum);//获取每一个单元格具体值，可根据需求转成任意数据类型

                        }
                    }
                    mapPointList.add(mapPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        questionsService.insertQuestions(mapPointList);

        modelMap.put("success",true);
        return modelMap;
    }*/














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
