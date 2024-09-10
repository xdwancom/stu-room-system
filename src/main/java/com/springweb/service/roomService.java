package com.springweb.service;
import com.springweb.pojo.Result;
import com.springweb.pojo.Room;
import java.util.List;

/**
 * 宿舍管理
 */
public interface roomService {
    
    void deleteid(List<String> ids);

    Result add(Room room);

    List<Room> search(Room room);

    void update(Room room);
}
