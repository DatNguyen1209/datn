package com.datn.controller;

import com.datn.dto.RoomDTO;
import com.datn.entities.Room;
import com.datn.serviceimpl.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/getAllRoom")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }
    @GetMapping("/getbyId/{id}")
    public Room  getById(@PathVariable("id") Long id){
        return roomService.findById(id);
    }
    @PostMapping("create")
    public void create(@RequestBody RoomDTO dto){
        roomService.save(dto);
    }
    @PostMapping("/createimg")
    @Transactional(rollbackOn = Exception.class)
    public String createimg(@RequestParam("files")MultipartFile[] files,@RequestParam("id") Long id){
        return roomService.save(files,id);
    }
    @PutMapping("/update/{id}")
    public RoomDTO updateRoom(@RequestBody RoomDTO dto,@PathVariable("id") Long id){
        dto.setId(id);
        return roomService.updateRoom(dto,id);
    }
    @PutMapping("/updateroomimg/{id}")
    public RoomDTO updateRoomImg(@RequestParam("files") MultipartFile[] files,@PathVariable("id") Long id){
        return roomService.updateRoomImg(files,id);
    }
    @PutMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        roomService.deleteById(id);
    }
    @PutMapping("/restore/{id}")
    public void  restore(@PathVariable Long id){
        roomService.restore(id);
    }
}
