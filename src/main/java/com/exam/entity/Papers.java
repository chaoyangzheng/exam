package com.exam.entity;


/**
 * 考生试卷
 *
 * @author rongjing
 * @date 2019/10/15
 */
public class Papers {

    //试卷表id
    private String papersTableId;

    //试卷id
    private String papersId;

    //科目id
    private Integer subjectId;

    //题目id
    private String questionsId;

    //考生id
    private String studentId;

    //考生答案
    private String studentAnswer;

    //考生该题得分
    private Double questionScore;

    /**
     * 题目
     *
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    private Questions questions;


    public Papers() {
    }

    public Papers(String papersTableId, String papersId, Integer subjectId, String questionsId, String studentId, String studentAnswer, Double questionScore) {
        this.papersTableId = papersTableId;
        this.papersId = papersId;
        this.subjectId = subjectId;
        this.questionsId = questionsId;
        this.studentId = studentId;
        this.studentAnswer = studentAnswer;
        this.questionScore = questionScore;
    }

    public String getPapersTableId() {
        return papersTableId;
    }

    public void setPapersTableId(String papersTableId) {
        this.papersTableId = papersTableId;
    }

    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Double getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Double questionScore) {
        this.questionScore = questionScore;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}
