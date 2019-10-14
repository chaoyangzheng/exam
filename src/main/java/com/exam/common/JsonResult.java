package com.exam.common;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public class JsonResult {
    private Integer code;
    private String msg;
    private Long count;//layui分页总记录数
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
