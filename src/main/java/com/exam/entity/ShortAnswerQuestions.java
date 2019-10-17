package com.exam.entity;

/**
 * 用于存储未被批改的简答题
 *
 * @author zhangyuanzhe
 * @date 2019/10/16
 */
public class ShortAnswerQuestions {
    private String papersId; // 试卷 ID
    private String questionsId; // 题目 ID
    private String questionsInfo; // 题干
    private String studentAnswer; // 考生答案
    private String questionsAnswer; // 标准答案

    public ShortAnswerQuestions() {
    }

    public ShortAnswerQuestions(String papersId, String questionsId, String questionsInfo, String studentAnswer, String questionsAnswer) {
        this.papersId = papersId;
        this.questionsId = questionsId;
        this.questionsInfo = questionsInfo;
        this.studentAnswer = studentAnswer;
        this.questionsAnswer = questionsAnswer;
    }

    @Override
    public String toString() {
        return "ShortAnswerQuestions{" +
                "papersId='" + papersId + '\'' +
                ", questionsId='" + questionsId + '\'' +
                ", questionsInfo='" + questionsInfo + '\'' +
                ", studentAnswer='" + studentAnswer + '\'' +
                ", questionsAnswer='" + questionsAnswer + '\'' +
                '}';
    }

    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public String getQuestionsInfo() {
        return questionsInfo;
    }

    public void setQuestionsInfo(String questionsInfo) {
        this.questionsInfo = questionsInfo;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getQuestionsAnswer() {
        return questionsAnswer;
    }

    public void setQuestionsAnswer(String questionsAnswer) {
        this.questionsAnswer = questionsAnswer;
    }
}
