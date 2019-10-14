package com.exam.entity;

import java.io.Serializable;

/**
 * 考场
 *
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public class Room implements Serializable {
    private String id;//教室id，uuid
    private String roomName;//教室名称地址
    private Integer seatNum;//座位数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }
}
