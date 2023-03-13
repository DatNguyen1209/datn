package com.datn.service;

import com.datn.dto.HotelDTO;
import com.datn.entities.Hotel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHotelService {
    public HotelDTO save(HotelDTO dto);
    public String save(MultipartFile[] file,Long id);
    public HotelDTO update(HotelDTO dto, Long id);
    public HotelDTO update(MultipartFile[] file, Long dto);
    public Hotel findById(Long id);
    public void  deleteAll();
    public String deleteById(Long id);
    public byte[] readContentFile(String  fileName);
    public List<Hotel> loadAll();
    public String reStore(Long id);
    public String deleteOut( Long id);
}
