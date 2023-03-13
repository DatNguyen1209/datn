package com.datn.serviceimpl;

import com.datn.converter.HotelConverter;
import com.datn.dto.HotelDTO;
import com.datn.entities.Hotel;
import com.datn.entities.Images;
import com.datn.repository.HotelRepository;
import com.datn.repository.ImageRepository;
import com.datn.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class HotelService implements IHotelService {
    private final Path root = Paths.get("datn/uploads");

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private HotelConverter converter;

    @Override
    public HotelDTO save(HotelDTO dto) {
        Hotel result = converter.toEntities(dto);
        if(dto.getId() != null){
            Optional<Hotel> optional = hotelRepository.findById(dto.getId());
            if(optional.isPresent()){
                throw new RuntimeException("Already exists");
            }
            result  = optional.get();
        }

        return converter.toDTO(hotelRepository.save(result));
    }

    @Override
    public String save(MultipartFile[] files,Long idd) {

        try{
            List<Images> images =new ArrayList<>();
            for (MultipartFile multipartFile : files){
                Hotel h = new Hotel();
                h.setId(idd);

                String name =UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
                try {
                    if(!Files.exists(root)){
                        Files.createDirectories(root);
                    }
                    Files.copy(multipartFile.getInputStream(),
                            this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                images.add(new Images(null,name,h));
            }
            imageRepository.saveAll(images);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Oki";
    }

    @Override
    public HotelDTO update(HotelDTO dto, Long id) {
        Hotel result = new Hotel();
        if(dto.getId() != null){
            Optional<Hotel> hotel = hotelRepository.findById(dto.getId());
            result = converter.toEntities(dto,hotel.get());
        }else {
            result = converter.toEntities(dto);
        }
        result = hotelRepository.save(result);
        return converter.toDTO(result);
    }

    @Override
    public HotelDTO update(MultipartFile files[], Long id) {
        Hotel h = this.findById(id);
        if(h.getImages()!= null){
            System.out.println("logger @@");
            h.getImages().stream().forEach((i)->{
                try {
                    imageRepository.deleteById(i.getId());
                    Files.deleteIfExists(this.root.resolve(i.getUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        List<Images> images =new ArrayList<>();
        for (MultipartFile multipartFile : files){
            String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
            try {
                if(Files.exists(root) == false){
                    Files.createDirectories(root);
                    System.err.println("Logger");
                }
                Files.copy(multipartFile.getInputStream(),
                        this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(new Images(null,name,h));
        }
        imageRepository.saveAll(images);

        return converter.toDTO(h);
    }
    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).
                orElseThrow(()->new RuntimeException("Not found"));
    }

    @Override
    public void deleteAll() {

        hotelRepository.deleteAll();
    }

    @Override
    public String deleteById(Long id) {
        Hotel h = this.findById(id);
        h.setStatus(false);
        hotelRepository.save(h);
        return "Deleted";
    }

    @Override
    public byte[] readContentFile(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                byte[] bytes =StreamUtils.copyToByteArray(resource.getInputStream());
                return  bytes;
            }else {
                throw new RuntimeException("Can not read file " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hotel> loadAll() {
        return hotelRepository.findAll();
    }

    @Override
    public String reStore(Long id) {
        Hotel h = this.findById(id);
        h.setStatus(true);
        hotelRepository.save(h);
        return "Restored";
    }

    @Override
    public String deleteOut( Long id) {
        Hotel h = this.findById(id);
        if(h.getImages()!= null){
            System.out.println("logger @@");
            h.getImages().stream().forEach((i)->{
                try {
                    imageRepository.deleteById(i.getId());
                    Files.deleteIfExists(this.root.resolve(i.getUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        hotelRepository.deleteById(id);
        return "Deleted";
    }

}
