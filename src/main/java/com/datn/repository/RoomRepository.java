package com.datn.repository;

import com.datn.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM Room WHERE room_name = ?1")
    Room findRoomCurrentByName(String roomName);

}
