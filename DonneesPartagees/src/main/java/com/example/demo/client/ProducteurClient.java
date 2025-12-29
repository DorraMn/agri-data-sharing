package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producteurs")
public interface ProducteurClient {

    @GetMapping("/api/producteurs/{id}/role")
    String getRole(@PathVariable("id") Long id);
    
    
    @GetMapping("/api/producteurs/{id}/can-publish")
    boolean canPublish(@PathVariable("id") Long id);

}
