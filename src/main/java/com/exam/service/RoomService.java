package com.exam.service;

import com.exam.entity.Room;

import java.util.List;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public interface RoomService {
    /**
     * 查询所有考场
     *
     * @return 所有考场的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    List<Room> selectAll();

    /**
     * 根据考场id查询考场
     *
     * @param id 考场id
     * @return 考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    Room selectById(String id);

    /**
     * 修改指定考场
     *
     * @param room 被修改的考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    void updateRoom(Room room);

    /**
     * 添加考场
     *
     * @param room 新添加的考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    void insertRoom(Room room);
}
