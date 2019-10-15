package com.exam.entity;




/**
 * 科目
 *
 * @author rongjing
 * @date 2019/10/14
 */

public class Subject {


    //科目id
    private Integer subjectId;

    //科目名
    private String subjectName;

    //由此判断一级，二级。一级语数英，二级四六级等
    private String parentId;


    public Subject() {
    }

    public Subject(Integer subjectId, String subjectName, String parentId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.parentId = parentId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
