package com.exam.entity;

import java.io.Serializable;

public class ExamnieeInfo implements Serializable {
    private String eId;
    private String examnieeId;
    private String examnieeName;
    private String examnieeSex;
    private String examnieePhoto;
    private String examnieeSubjectId;

    @Override
    public String toString() {
        return "ExamnieeInfo{" +
                "eId='" + eId + '\'' +
                ", examnieeId='" + examnieeId + '\'' +
                ", examnieeName='" + examnieeName + '\'' +
                ", examnieeSex='" + examnieeSex + '\'' +
                ", examnieePhoto='" + examnieePhoto + '\'' +
                ", examnieeSubjectId='" + examnieeSubjectId + '\'' +
                '}';
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getExamnieeId() {
        return examnieeId;
    }

    public void setExamnieeId(String examnieeId) {
        this.examnieeId = examnieeId;
    }

    public String getExamnieeName() {
        return examnieeName;
    }

    public void setExamnieeName(String examnieeName) {
        this.examnieeName = examnieeName;
    }

    public String getExamnieeSex() {
        return examnieeSex;
    }

    public void setExamnieeSex(String examnieeSex) {
        this.examnieeSex = examnieeSex;
    }

    public String getExamnieePhoto() {
        return examnieePhoto;
    }

    public void setExamnieePhoto(String examnieePhoto) {
        this.examnieePhoto = examnieePhoto;
    }

    public String getExamnieeSubjectId() {
        return examnieeSubjectId;
    }

    public void setExamnieeSubjectId(String examnieeSubjectId) {
        this.examnieeSubjectId = examnieeSubjectId;
    }
}
