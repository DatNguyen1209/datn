package com.datn.serviceimpl;

import com.datn.converter.ServiceConverter;
import com.datn.dto.ServiceDTO;
import com.datn.entities.Hotel;
import com.datn.entities.Service;
import com.datn.repository.HotelRepository;
import com.datn.repository.ServiceRepository;
import com.datn.service.IImagesHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ServiceService implements IImagesHotel {
    private final Path root  = Paths.get("datn/uploads");
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ServiceConverter converter;
    @Override
    public String save(MultipartFile[] files,Long id) {
        try {
            List<Service> services = new ArrayList<>();
            for(MultipartFile multipartFile:files){
                String name = UUID.randomUUID().toString() + multipartFile.getOriginalFilename();
                Service service = new Service();
                service.setId(id);
                try {
                    if(Files.exists(root) == false){
                        Files.createDirectories(root);
                    }
                    Files.copy(multipartFile.getInputStream(),
                            this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                services.add(new Service(name,service.isStatus(),hotelRepository.getReferenceById(service.getId())));
            }
            serviceRepository.saveAll(services);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return "Oki";
    }

    @Override
    public String update(MultipartFile[] files, Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!!!");
        });
        if(hotel.getServices()!= null){
            hotel.getServices().stream().forEach((i)->{
                try {
                    serviceRepository.deleteById(i.getId());
                    Files.deleteIfExists(this.root.resolve(i.getUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        List<Service> services = new ArrayList<>();
        for (MultipartFile multipartFile:files){
            String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
            try {
                if(Files.exists(root) == false){
                    Files.createDirectories(root);
                }
                Files.copy(multipartFile.getInputStream(),
                        this.root.resolve(name),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            services.add(new Service(name, hotel.isStatus(), hotel));
        }
        serviceRepository.saveAll(services);
        return "Ok";
    }

    @Override
    public String delete(Long id) {
        Service service = serviceRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!!!");
        });
        service.setStatus(false);
        serviceRepository.save(service);
        return "Deleted";
    }
}
