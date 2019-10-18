package com.exam.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.exam.common.JsonResult;
import com.exam.entity.QuestionType;
import com.exam.entity.Questions;
import com.exam.service.PapersService;
import com.exam.service.QuestionsService;
import com.exam.utils.UploadUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.javascript.navig4.Layer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 题库管理
 *
 * @author RongJing
 * @date 2019/10/16
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired(required = false)
    private QuestionsService questionsService;

    @Autowired(required = false)
    private UploadUtil upload;

    @Autowired
    private PapersService papersService;

    /**
     * 查看所有试题
     *
     * @param page 当前页码
     * @param  limit 每页条数
     * @return code=0,msg="查询成功"，count=所有数据，data=当前页所有试题的list
     * @author RongJing
     * @date 2019/10/17
     */
@RequestMapping("/questionsAll.do")
public JsonResult questionsList(Integer page,Integer limit){
    List<Questions> questionsList = questionsService.findAllQuestions(page,limit);
    Long count = ((Page) questionsList).getTotal();
    return new JsonResult(0,"查询成功",count,questionsList);
}

    @RequestMapping("/findBySubject.do")
    public JsonResult findBySubject(Integer questionsSubjectId,Integer page,Integer limit){
        List<Questions> questionsList = questionsService.findBySubjectId(questionsSubjectId,page,limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0,"查询成功",count,questionsList);
    }


    @RequestMapping("/findByUploadTeacher.do")
    public JsonResult findByUploadTeacher(String uploadTeacherId,Integer page,Integer limit){
        List<Questions> questionsList = questionsService.findByUploadTeacherId(uploadTeacherId,page,limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0,"查询成功",count,questionsList);
    }

    @RequestMapping("/findByUploadTime.do")
    public JsonResult findByUploadTime(String uploadTime,Integer page,Integer limit){
        List<Questions> questionsList = questionsService.findByUploadTime(uploadTime,page,limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0,"查询成功",count,questionsList);
    }

    @RequestMapping("/findByQuestionsType.do")
    public JsonResult findByQuestionsType(Integer questionsTypeId,Integer page,Integer limit){
        List<Questions> questionsList = questionsService.findByQuestionsTypeId(questionsTypeId,page,limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0,"查询成功",count,questionsList);
    }


    @RequestMapping("/findAllType.do")
    @ResponseBody
    public JsonResult findAllType(){
        List<QuestionType> allQuestionsType = questionsService.findAllQuestionsType();

        return new JsonResult(0,"查询成功",null,allQuestionsType);
    }


    @RequestMapping("/insertOne.do")
    @ResponseBody
    public JsonResult insertOne(Questions questions,HttpServletRequest request){


    if(questions.getQuestionsTypeId() != null || "".equals(questions.getQuestionsTypeId())){
        if(questions.getQuestionsTypeId() == 1 || questions.getQuestionsTypeId() == 0){
            questions.setQuestionsAnswer(request.getParameter("title") + "%-" + request.getParameter("titleA") + "%-" + request.getParameter("titleB") + "%-" +  request.getParameter("titleC") + "%-" +  request.getParameter("titleD"));
        }
        questionsService.insertQuestions(questions);

        return new JsonResult(0,"已添加成功",null,null);
    }else {
        return new JsonResult(1,"请选择试题类型",null,null);
    }







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
