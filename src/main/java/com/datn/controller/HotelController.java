package com.datn.controller;

import com.datn.converter.HotelConverter;
import com.datn.dto.HotelDTO;
import com.datn.entities.Hotel;
import com.datn.repository.HotelRepository;
import com.datn.repository.ImageRepository;
import com.datn.serviceimpl.HotelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelService hotelService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    private HotelConverter converter;

    @PostMapping("/createhotel")
    public HotelDTO createHotel(@RequestBody HotelDTO dto){
        return hotelService.save(dto);
    }
    @PostMapping("/create")
    @Transactional(rollbackOn = Exception.class)
    public String createHotel(@RequestParam("files")MultipartFile[] file,@RequestParam("id")Long id){
        return hotelService.save(file,id);
    }
    @GetMapping("/getAll")
    public List<Hotel> getAllHotel(){
        return hotelService.loadAll();
    }
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable("id") Long id){
        return hotelService.findById(id);
    }
//    @GetMapping("/uploads/{fileName}")
//    public ResponseEntity<byte[]> readFile(@PathVariable String fileName){
//        try {
//            byte[] bytes = hotelService.readContentFile(fileName);
//            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
//        }catch (Exception e){
//            return ResponseEntity.noContent().build();
//        }
//    }
    @PutMapping("/update/{id}")
    public HotelDTO updateHotel(@RequestParam("files")MultipartFile[] files,@PathVariable("id") Long id){
        return hotelService.update(files,id);
    }
    @PutMapping("updated/{id}")
    public HotelDTO updateHotel(@RequestBody HotelDTO dto, @PathVariable("id") Long id){
        dto.setId(id);
        return hotelService.update(dto,id);
    }
    @DeleteMapping("/deleteAll")
    public void DeleteAll(){
         hotelService.deleteAll();
    }
    @PutMapping("/delete/{id}")
    public String deleteHotelById(@PathVariable("id") Long id){
        hotelService.deleteById(id);
        return "Moved to backup!";
    }
    @PutMapping("/restore/{id}")
    public  String reStoreById(@PathVariable("id") Long id){
        hotelService.reStore(id);
        return "Restored Successfully!";
    }
    @DeleteMapping("/deleteout/{id}")
    public  String deleteOut(@PathVariable("id") Long id){
        hotelService.deleteOut(id);
        return "Deleted Successfully!";
    }
}
