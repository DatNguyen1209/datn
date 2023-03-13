package com.datn.service;

import com.datn.dto.RoomDTO;
import com.datn.entities.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRoomService {
    void save(RoomDTO dto);
    String save(MultipartFile[] files,Long id);
    RoomDTO updateRoom(RoomDTO dto, Long id);
    RoomDTO updateRoomImg(MultipartFile[] files, Long id);
    Room findById(Long id);
    void deleteById(Long id);
    void restore(Long id);
    List<Room> getAllRoom();
}
