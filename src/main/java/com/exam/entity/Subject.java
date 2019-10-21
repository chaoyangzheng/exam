package com.exam.entity;

/**
 * @author RongJing
 * @date 2019/10/15
 */
public class Subject {


    //科目id
    private Integer subjectId;

    //科目名
    private String subjectName;

    //根据此判断一二级科目，一级：语数英，二级四六级。为空时是一级
    private Integer parentId;

    private Subject subjectPar;


    public Subject() {
    }

    public Subject(Integer subjectId, String subjectName, Integer parentId, Subject subjectPar) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.parentId = parentId;
        this.subjectPar = subjectPar;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Subject getSubjectPar() {
        return subjectPar;
    }

    public void setSubjectPar(Subject subjectPar) {
        this.subjectPar = subjectPar;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", parentId=" + parentId +
                ", subjectPar=" + subjectPar +
                '}';
    }
}
