package com.exam.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试场次
 *
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public class ExamSession implements Serializable {
    private String id;//考试场次id，uuid，主键
    private String subjectId;//科目id，外键
    private String teacherId;//考试申请老师id，外键
    private Integer studentNum;//考生人数上限
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;//考试开始日期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//考试结束日期时间
    private Integer duringTime;//考试时长，单位分钟

    private Subject subject;//科目
    private User teacher;//考试申请老师

    @Override
    public String toString() {
        return "ExamSession{" +
                "id='" + id + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", studentNum=" + studentNum +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", duringTime=" + duringTime +
                ", subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(Integer duringTime) {
        this.duringTime = duringTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
