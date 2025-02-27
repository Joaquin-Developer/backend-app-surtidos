package com.appsurtidos.backend.controllers;

import com.appsurtidos.backend.models.ScrapperProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @PostMapping("/scrapping-products")
    public String receiveProducts(@RequestBody List<ScrapperProduct> products) {
        System.out.println("Total products: " + products.size());
        for (ScrapperProduct product : products) {
            System.out.println(product.toString());
        }

        return "OK!!";
    }
}
