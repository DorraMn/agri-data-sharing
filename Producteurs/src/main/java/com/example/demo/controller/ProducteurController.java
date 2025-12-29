package com.example.demo.controller;

import com.example.demo.entity.Producteur;
import com.example.demo.service.ProducteurService;
import com.example.demo.service.MessageProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producteurs")
public class ProducteurController {

    private final ProducteurService service;
    private final MessageProducer messageProducer;

    public ProducteurController(ProducteurService service,
                                MessageProducer messageProducer) {
        this.service = service;
        this.messageProducer = messageProducer;
    }

    // ---------------- CRUD Producteurs ----------------

    @PostMapping
    public Producteur create(@RequestBody Producteur producteur) {
        return service.save(producteur);
    }

    @GetMapping
    public List<Producteur> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Producteur getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ---------------- Communication synchrone (Feign) ----------------

    @GetMapping("/{id}/role")
    public String getRole(@PathVariable Long id) {
        return service.getRoleById(id);
    }

    @GetMapping("/{id}/can-publish")
    public boolean canPublish(@PathVariable Long id) {
        return service.hasPublishingRight(id);
    }

    // ---------------- Communication asynchrone (RabbitMQ) ----------------

    @PostMapping("/{id}/publish")
    public String publishDataset(@PathVariable Long id,
                                 @RequestBody Object payload) {

        payload = new MessagePayload(id, payload);

        messageProducer.publish(payload);

        return "Dataset publié et message envoyé à RabbitMQ";
    }

    // ----------- DTO interne pour message JSON ----------
    private record MessagePayload(Long producteurId, Object data) {}
}
