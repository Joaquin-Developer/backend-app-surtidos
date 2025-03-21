package com.appsurtidos.backend.controllers;

import com.appsurtidos.backend.dto.ProductPurchaseDTO;
import com.appsurtidos.backend.models.ProductPurchase;
import com.appsurtidos.backend.models.Purchase;
import com.appsurtidos.backend.service.PurchaseService;
import com.appsurtidos.backend.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final JwtUtil jwtUtil;

    public PurchaseController(PurchaseService purchaseService, JwtUtil jwtUtil) {
        this.purchaseService = purchaseService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            Purchase purchase = purchaseService.createPurchase(username);
            return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not found");
        }
    }

//    @PatchMapping("/{purchaseId}/finalize")
//    public ResponseEntity<?> finalizePurchase(@PathVariable Long purchaseId) {
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
//    }

    @PostMapping("/{purchaseId}/products")
    public ResponseEntity<ProductPurchase> addProductToPurchase(
            @PathVariable Long purchaseId,
            @RequestBody ProductPurchaseDTO request) {

        ProductPurchase productPurchase = purchaseService.addProductToPurchase(purchaseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productPurchase);
    }

    // TODO implement:


    @PutMapping("/{purchaseId}/products/{productPurchaseId}")
    public ResponseEntity<?> updateProductInPurchase(@PathVariable Long purchaseId, @PathVariable Long productPurchaseId, @RequestBody Map<String, Object> updateData) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{purchaseId}/products")
    public ResponseEntity<?> getProductsFromPurchase(@PathVariable Long purchaseId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
