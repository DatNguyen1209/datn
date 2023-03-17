package com.datn.repository;

import com.datn.entities.Hotel;
import com.datn.entities.Images;
import com.datn.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM Room WHERE room_name = ?1")
    Hotel findRoomCurrentByName(String roomName);

}
