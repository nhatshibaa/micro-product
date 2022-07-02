package com.example.demo_product_choreogrphy.controller;

import com.example.demo_product_choreogrphy.entity.Product;
import com.example.demo_product_choreogrphy.repository.ProductRepository;
import com.example.demo_product_choreogrphy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")
public class ProductApi {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "name", defaultValue = "") String name){
        return ResponseEntity.ok(productService.findAllByName(
                        PageRequest.of(page - 1, limit), name
                )
        );
    }

//    @RequestMapping(value = "/deductProduct", method = RequestMethod.POST)
//    public ResponseEntity<?> deductProduct(
//            @RequestBody Product product
//            ) {
//        return new ResponseEntity(productService.deductProduct(product), HttpStatus.OK);
//    }
}
