package com.datn.serviceimpl;

import com.datn.converter.RoomConverter;
import com.datn.dto.RoomDTO;
import com.datn.entities.ImagesRoom;
import com.datn.entities.Room;
import com.datn.repository.HotelRepository;
import com.datn.repository.ImageRoomRepository;
import com.datn.repository.RoomRepository;
import com.datn.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class RoomService implements IRoomService {

    private final Path root = Paths.get("datn/uploads");
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ImageRoomRepository imageRoomRepository;
    @Autowired
    private  HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomConverter converter;
    @Override
    public void save(RoomDTO dto) {
//        Room room = null;
//        var hotelId = hotelService.findById(dto.getHotelId());
//        if(hotelId.getId() != null){
//             room = converter.toEntity(dto);
//
//        }
//        room.setHotelId(hotelId);
//        roomRepository.save(room);
//        return converter.toDTO(room);
        try {
            Room room = null;
            var roomFound = roomRepository.findRoomCurrentByName(dto.getRoomName());
            if(roomFound != null){
                throw new RuntimeException("Room name already exist!!!");
            }else {
                room = new Room();
                room.setId(dto.getRoomId());
                room.setRoomName(dto.getRoomName());
                room.setPrice(dto.getPrice());
                room.setCapacity(dto.getCapacity());
                room.setCreatedDate(new Date());
                room.setBedType(dto.getBedType());
                room.setStatus(dto.isStatus());
                room.setHotelId(hotelRepository.getReferenceById(dto.getHotelId()));
                roomRepository.save(room);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String save(MultipartFile[] files, Long id) {
        try {
            List<ImagesRoom> imagesRooms = new ArrayList<>();
            for (MultipartFile multipartFile: files){
                String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
                Room room = new Room();
                room.setId(id);
                try {
                    if(Files.exists(root) == false){
                        Files.createDirectories(root);
                    }
                    Files.copy(multipartFile.getInputStream(),
                            this.root.resolve(name),StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imagesRooms.add(new ImagesRoom(null,name,room));
            }
            imageRoomRepository.saveAll(imagesRooms);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
        return "Oki";
    }

    @Override
    public RoomDTO updateRoom(RoomDTO dto, Long id) {
        Room result =new Room();
        if(dto.getId() != null){
            Optional<Room> room = roomRepository.findById(dto.getId());
            result = converter.toEntities(dto,room.get());
        }else {
            throw new RuntimeException("Not Found!!!");
        }
        result = roomRepository.save(result);
        return converter.toDTO(result);
    }

    @Override
    public RoomDTO updateRoomImg(MultipartFile[] files, Long id) {
        Room room = roomRepository.findById(id).orElseThrow(()->{
            throw  new RuntimeException("Not Found Room");
        });
        if(room.getRooms() != null){
            room.getRooms().stream().forEach((i)->{
                try {
                    imageRoomRepository.deleteById(i.getId());
                    Files.deleteIfExists(this.root.resolve(i.getUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        List<ImagesRoom> imagesRooms = new ArrayList<>();
        for (MultipartFile multipartFile: files){
            String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
            try {
                if(Files.exists(root) == false){
                    Files.createDirectories(root);
                }
                Files.copy(multipartFile.getInputStream(),
                        this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imagesRooms.add(new ImagesRoom(null,name,room));
        }
        imageRoomRepository.saveAll(imagesRooms);
        return converter.toDTO(room);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!!!");
        });
    }

    @Override
    public void deleteById(Long id) {
        Room room = this.findById(id);
        room.setStatus(false);
        roomRepository.save(room);
    }

    @Override
    public void restore(Long id) {
        Room room = this.findById(id);
        room.setStatus(true);
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRoom() {

        return roomRepository.findAll();
    }
}
