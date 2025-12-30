package com.example.demo.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducteurDTO {
    private Long id;
    private String nom;
    private String email;
    private String region;
    private String role;
}
