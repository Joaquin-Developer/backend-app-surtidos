package com.appsurtidos.backend.service;

import com.appsurtidos.backend.dto.ProductPurchaseDTO;
import com.appsurtidos.backend.models.ProductPurchase;
import com.appsurtidos.backend.models.Purchase;
import com.appsurtidos.backend.models.User;
import com.appsurtidos.backend.repository.ProductPurchaseRepository;
import com.appsurtidos.backend.repository.PurchaseRepository;
import com.appsurtidos.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductPurchaseRepository productPurchaseRepository;

    // constants
    private final String DEFAULT_CURRENCY_CODE = "$";
    private final int DEFAULT_QUANTITY_VALUE = 1;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, ProductPurchaseRepository productPurchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productPurchaseRepository = productPurchaseRepository;
    }

    public Purchase createPurchase(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setCreatedAt(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    @Transactional
    public ProductPurchase addProductToPurchase(Long purchaseId, ProductPurchaseDTO request) {
        // Validate purchase existence
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        // Validate that at least one of the fields product_id_scraper or product_manual_name is present
        if (request.getProductIdScraper() == null && (request.getProductManualName() == null || request.getProductManualName().trim().isEmpty())) {
            throw new IllegalArgumentException("Either productIdScraper or productManualName must be provided.");
        }

        // crete and save ProductPurchase object
        ProductPurchase productPurchase = new ProductPurchase();
        productPurchase.setPurchase(purchase);
        productPurchase.setProductIdScraper(request.getProductIdScraper());
        productPurchase.setProductManualName(request.getProductManualName());
        productPurchase.setQuantity(request.getQuantity() != null ? request.getQuantity() : DEFAULT_QUANTITY_VALUE);
        productPurchase.setCurrency(request.getCurrency() != null ? request.getCurrency() : DEFAULT_CURRENCY_CODE);
        productPurchase.setPrice(request.getPrice());
        return productPurchaseRepository.save(productPurchase);
    }
}
