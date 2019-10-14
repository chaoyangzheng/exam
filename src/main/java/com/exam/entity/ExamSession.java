package com.exam.entity;

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
    private String examName;//考试名称
    private String subjectId;//科目id，外键
    private String teacherId;//考试创建老师id，外键
    private String roomId;//考场教室id，外键
    private Integer studentNum;//考生人数上限
    private Date beginTime;//考试开始日期时间
    private Date endTime;//考试结束日期时间
    private Integer duringTime;//考试时长，单位分钟

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
}
