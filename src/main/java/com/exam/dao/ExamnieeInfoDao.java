package com.exam.dao;

import com.exam.entity.ExamnieeInfo;

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
    public List<ExamnieeInfo> findExamnieeInfoById(String examnieeId);

    //按名字查学生
    public ExamnieeInfo findExamnieeInfoByName(String examnieeName);

    //按名字查学生的集合
    public List<ExamnieeInfo> findExamnieeInfoByNames(String examnieeName);

    //按学科查询学声的集合
    public List<ExamnieeInfo> findExamnieeInfoBySubjectName(String subjectName);

    //删除一个集合的学生
    public int deleteExamnieeInfoByIds(List<String> ids);

    //连表查询学科名的考生集合
    public List<ExamnieeInfo> findAllExamnieeInfoAndSubjectName();

    /**
     * 查询报考该考试场次的学生
     *
     * @param examSessionId 考试场次id
     * @return 考生list
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    List<ExamnieeInfo> findExamnieeInfoByExamSessionId(String examSessionId);
}
