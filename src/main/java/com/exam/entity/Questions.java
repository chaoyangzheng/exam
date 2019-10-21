package com.exam.entity;



import java.io.Serializable;
import java.util.Date;

/**
 * 题库
 *
 * @author rongjing
 * @date 2019/10/14
 */

public class Questions implements Serializable {


    //题id

    private String questionsId;

    //试题科目

    private Integer questionsSubjectId;

    //试题类别
    private Integer questionsTypeId;

    //试题题干

    private String questionsInfo;

    //试题答案

    private String questionsAnswer;

    //试题上传时间
    private Date uploadTime;

    //出题人
    private String uploadTeacherId;

    private Subject subject;
    private  QuestionType questionType;
    private  User user;




    public Questions() {
    }

    public Questions(String questionsId, Integer questionsSubjectId, Integer questionsTypeId, String questionsInfo, String questionsAnswer, Date uploadTime, String uploadTeacherId, Subject subject, QuestionType questionType, User user) {
        this.questionsId = questionsId;
        this.questionsSubjectId = questionsSubjectId;
        this.questionsTypeId = questionsTypeId;
        this.questionsInfo = questionsInfo;
        this.questionsAnswer = questionsAnswer;
        this.uploadTime = uploadTime;
        this.uploadTeacherId = uploadTeacherId;
        this.subject = subject;
        this.questionType = questionType;
        this.user = user;
    }


    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public Integer getQuestionsSubjectId() {
        return questionsSubjectId;
    }

    public void setQuestionsSubjectId(Integer questionsSubjectId) {
        this.questionsSubjectId = questionsSubjectId;
    }

    public Integer getQuestionsTypeId() {
        return questionsTypeId;
    }

    public void setQuestionsTypeId(Integer questionsTypeId) {
        this.questionsTypeId = questionsTypeId;
    }

    public String getQuestionsInfo() {
        return questionsInfo;
    }

    public void setQuestionsInfo(String questionsInfo) {
        this.questionsInfo = questionsInfo;
    }

    public String getQuestionsAnswer() {
        return questionsAnswer;
    }

    public void setQuestionsAnswer(String questionsAnswer) {
        this.questionsAnswer = questionsAnswer;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadTeacherId() {
        return uploadTeacherId;
    }

    public void setUploadTeacherId(String uploadTeacherId) {
        this.uploadTeacherId = uploadTeacherId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Questions{" +
                "questionsId='" + questionsId + '\'' +
                ", questionsSubjectId=" + questionsSubjectId +
                ", questionsTypeId=" + questionsTypeId +
                ", questionsInfo='" + questionsInfo + '\'' +
                ", questionsAnswer='" + questionsAnswer + '\'' +
                ", uploadTime=" + uploadTime +
                ", uploadTeacherId='" + uploadTeacherId + '\'' +
                ", subject=" + subject +
                ", questionType=" + questionType +
                ", user=" + user +
                '}';
    }
}
