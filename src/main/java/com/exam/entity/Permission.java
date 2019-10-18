package com.exam.entity;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class Permission {
    private Integer permId;
    private String permName;
    private String permDesc;
    private String type;
    private String url;
    private Integer parentId;

    @Override
    public String toString() {
        return "Permission{" +
                "permId=" + permId +
                ", permName='" + permName + '\'' +
                ", permDesc='" + permDesc + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
