package com.exam.service.impl;

import com.exam.dao.RoomDao;
import com.exam.entity.Room;
import com.exam.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    /**
     * 查询所有考场
     *
     * @return 所有考场的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public List<Room> selectAll() {
        List<Room> roomList = null;
        roomList = roomDao.selectAll();
        return roomList;
    }

    /**
     * 根据考场id查询考场
     *
     * @param id 考场id
     * @return 考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public Room selectById(String id) {
        Room room = null;
        room = roomDao.selectById(id);
        return room;
    }

    /**
     * 修改指定考场
     *
     * @param room 被修改的考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public void updateRoom(Room room) {
        roomDao.updateRoom(room);
    }

    /**
     * 添加考场
     *
     * @param room 新添加的考场
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public void insertRoom(Room room){
        String id = UUID.randomUUID().toString().replace("-", "");
        room.setId(id);
        roomDao.insertRoom(room);
    }
}
