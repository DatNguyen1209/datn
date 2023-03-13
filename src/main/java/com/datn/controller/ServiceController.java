package com.datn.controller;

import com.datn.serviceimpl.ServiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    @Autowired
    private ServiceService service;
    @PostMapping("/createService/{id}")
    @Transactional(rollbackOn = Exception.class)
    public String createService(@RequestParam("files")MultipartFile[] files, @PathVariable("id") Long id){
        return service.save(files,id);
    }
    @PutMapping("/updateService/{id}")
        public String updateService(@RequestParam("files") MultipartFile[] files, @PathVariable("id") Long id){
        service.update(files,id);
        return "Ok";
    }
    @PutMapping("/delete/{id}")
    public String deleteService(@PathVariable("id") Long id){
        service.delete(id);
        return "Deleted";
    }
//    @GetMapping("/getAllService")

}
