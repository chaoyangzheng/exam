package com.exam.entity;

/**
 * 试题类型
 *
 * @author rongjing
 * @date 2019/10/17
 */
public class QuestionType {

    private Integer questionsTypeId;

    private  String questionsTypeName;

    public QuestionType() {
    }

    public QuestionType(Integer questionsTypeId, String questionsTypeName) {
        this.questionsTypeId = questionsTypeId;
        this.questionsTypeName = questionsTypeName;
    }

    public Integer getQuestionsTypeId() {
        return questionsTypeId;
    }

    public void setQuestionsTypeId(Integer questionsTypeId) {
        this.questionsTypeId = questionsTypeId;
    }

    public String getQuestionsTypeName() {
        return questionsTypeName;
    }

    public void setQuestionsTypeName(String questionsTypeName) {
        this.questionsTypeName = questionsTypeName;
    }
}
