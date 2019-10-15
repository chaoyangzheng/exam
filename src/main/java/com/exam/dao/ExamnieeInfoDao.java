package com.exam.dao;

import com.exam.entity.ExamnieeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
public interface ExamnieeInfoDao {
    /*zxs
    * */

    //查询所有考生信息
    public List<ExamnieeInfo> findAllExamnieeInfo();

    //查询所有考生信息的总记录数，layui分页需要
    public Long findAllExamnieeNumber();

    //增
    public int addExamnieeInfo(ExamnieeInfo examnieeInfo);

    //删,按身份证删
    public int deleteExamnieeInfoById(String examnieeId);

    //改
    public int updateExamnieeInfo(ExamnieeInfo examnieeInfo);

    //按eId查询学生
    public ExamnieeInfo findExamnieeInfoByEid(String eId);

    //按身份证查学生
    public ExamnieeInfo findExamnieeInfoById(String examnieeId);

    //按名字查学生
    public ExamnieeInfo findExamnieeInfoByName(String examnieeName);


}
