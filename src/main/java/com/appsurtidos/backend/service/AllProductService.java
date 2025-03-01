package com.appsurtidos.backend.service;

import com.appsurtidos.backend.dto.AllProductDTO;
import com.appsurtidos.backend.dto.LatestProductDTO;
import com.appsurtidos.backend.models.AllProducts;
import com.appsurtidos.backend.models.SupermarketChain;
import com.appsurtidos.backend.repository.AllProductRepository;
import com.appsurtidos.backend.repository.SupermarketChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllProductService {
    private final AllProductRepository productRepository;
    private final SupermarketChainRepository supermarketRepository;

    @Autowired
    public AllProductService(AllProductRepository productRepository, SupermarketChainRepository supermarketRepository) {
        this.productRepository = productRepository;
        this.supermarketRepository = supermarketRepository;
    }

    public void saveProduct(AllProductDTO dto) {
        SupermarketChain supermarket = supermarketRepository.findById(dto.getSupermarketChainId())
                .orElseThrow(() -> new RuntimeException("Supermarket chain not found"));

        Long ean = (dto.getEan() != null) ? dto.getEan() : -1;
        LocalDateTime scrapingDate = (dto.getScrapingDate() != null) ? dto.getScrapingDate() : LocalDateTime.now();

        AllProducts product = new AllProducts();
        product.setEan(ean);
        product.setScrapingDate(scrapingDate);
        product.setName(dto.getName());
        product.setImageLink(dto.getImageLink());
        product.setCurrency(dto.getCurrency());
        product.setPrice(dto.getPrice());
        product.setSupermarketChain(supermarket);

        productRepository.save(product);
    }

    public void saveProducts(List<AllProductDTO> products) {
        List<AllProducts> allProducts = products.stream().map(dto -> {
            SupermarketChain supermarket = supermarketRepository.findById(dto.getSupermarketChainId())
                    .orElseThrow(() -> new RuntimeException("Supermarket chain not found"));

            Long ean = (dto.getEan() != null) ? dto.getEan() : -1;
            LocalDateTime scrapingDate = (dto.getScrapingDate() != null) ? dto.getScrapingDate() : LocalDateTime.now();

            AllProducts product = new AllProducts();
            product.setEan(ean);
            product.setScrapingDate(scrapingDate);
            product.setName(dto.getName());
            product.setImageLink(dto.getImageLink());
            product.setCurrency(dto.getCurrency());
            product.setPrice(dto.getPrice());
            product.setSupermarketChain(supermarket);

            return product;
        }).collect(Collectors.toList());

        productRepository.saveAll(allProducts);
    }

    public List<LatestProductDTO> getLatestProducts(Long supermarketChainId) {
        List<Object[]> results = productRepository.findLatestProductsBySupermarket(supermarketChainId);

        return results.stream()
                .map(row -> new LatestProductDTO(
                        ((Timestamp) row[0]).toLocalDateTime(),  // scraping_date
                        (String) row[1],         // supermarket
                        (Long) row[2],           // ean
                        (String) row[3],         // name
                        (String) row[4]          // price
                ))
                .collect(Collectors.toList());
    }

}
