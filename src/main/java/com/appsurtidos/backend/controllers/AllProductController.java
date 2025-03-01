package com.appsurtidos.backend.controllers;

import com.appsurtidos.backend.dto.AllProductDTO;
import com.appsurtidos.backend.dto.LatestProductDTO;
import com.appsurtidos.backend.service.AllProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class AllProductController {
    private final AllProductService service;

    public AllProductController(AllProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody AllProductDTO productDTO) {
        service.saveProduct(productDTO);
        return ResponseEntity.ok("Product created sucessfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<String> saveProducts(@RequestBody List<AllProductDTO> products) {
        service.saveProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).body("Products saved successfully");
    }

    @GetMapping("/latest")
    public ResponseEntity<List<LatestProductDTO>> getLatestProducts(
            @RequestParam("supermarketChainId") Long supermarketChainId) {
        List<LatestProductDTO> products = service.getLatestProducts(supermarketChainId);
        return ResponseEntity.ok(products);
    }

}
