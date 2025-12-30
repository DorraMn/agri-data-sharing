package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Producteurs", url = "http://localhost:8081")
public interface ProducteurClient {
    
    @PostMapping("/api/producteurs")
    ProducteurDTO createProducteur(@RequestBody ProducteurDTO producteur);
}
