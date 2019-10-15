package com.exam.entity;

import java.io.Serializable;

/**
 * @author zhangyuanzhe
 * @date 2019/10/15
 */
public class Score implements Serializable {

    private String scoreId; // 成绩 ID
    private String studentId;// 考生 ID
    private String papersId;// 试卷 ID
    private Double score;// 总成绩

    private ExamnieeInfo examnieeInfo;
    private Subject subject;
    private Papers papers;

    public Score() {
    }

    public Score(String scoreId, String studentId, String papersId, Double score, ExamnieeInfo examnieeInfo, Subject subject, Papers papers) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.papersId = papersId;
        this.score = score;
        this.examnieeInfo = examnieeInfo;
        this.subject = subject;
        this.papers = papers;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId='" + scoreId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", papersId='" + papersId + '\'' +
                ", score=" + score +
                ", examnieeInfo=" + examnieeInfo +
                ", subject=" + subject +
                ", papers=" + papers +
                '}';
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ExamnieeInfo getExamnieeInfo() {
        return examnieeInfo;
    }

    public void setExamnieeInfo(ExamnieeInfo examnieeInfo) {
        this.examnieeInfo = examnieeInfo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Papers getPapers() {
        return papers;
    }

    public void setPapers(Papers papers) {
        this.papers = papers;
    }
}
