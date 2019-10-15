package com.exam.entity;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class Subject {
    private Integer subjectId;
    private String subjectName;
    private Integer parentId;

    @Override
    public String toString () {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getSubjectId () {
        return subjectId;
    }

    public void setSubjectId (Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName () {
        return subjectName;
    }

    public void setSubjectName (String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getParentId () {
        return parentId;
    }

    public void setParentId (Integer parentId) {
        this.parentId = parentId;
    }
}
