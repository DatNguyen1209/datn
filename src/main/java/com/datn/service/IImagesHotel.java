package com.datn.service;

import com.datn.dto.ServiceDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IImagesHotel {
    String save(MultipartFile[] files,Long id);
    String update(MultipartFile[] files, Long id);
    String delete(Long id);
    
}
