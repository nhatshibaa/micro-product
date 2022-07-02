package com.example.demo_product_choreogrphy.service;

import com.example.demo_product_choreogrphy.entity.Product;
import com.example.demo_product_choreogrphy.repository.ProductRepository;
import com.example.demo_product_choreogrphy.until.QueueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ProductService {
    final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Map<String, Object> findAllByName(Pageable pageable, String name) {
        Map<String, Object> responses = new HashMap<>();
        if (name != null || name != ""){
            Page<Product> pageTu = productRepository.findByNameContaining(pageable,name);
            List<Product> list = pageTu.getContent();
            responses.put("content",list); // chi tiết các phần tử được
            responses.put("currentPage",pageTu.getNumber() + 1);// trang hiện tại
            responses.put("totalItems",pageTu.getTotalElements());// tổng số phàn tử
            responses.put("totalPage",pageTu.getTotalPages()); // tổng só trang
        }else {
            Page<Product> pageTu = productRepository.findAll(pageable);
            List<Product> list = pageTu.getContent();
            responses.put("content",list); // chi tiết các phần tử được
            responses.put("currentPage",pageTu.getNumber() + 1);// trang hiện tại
            responses.put("totalItems",pageTu.getTotalElements());// tổng số phàn tử
            responses.put("totalPage",pageTu.getTotalPages()); // tổng só trang
        }
        return responses;
    }

    public Map<String, Object> findAll(Pageable pageable) {
        Map<String, Object> responses = new HashMap<>();
        Page<Product> pageTu = productRepository.findAll(pageable);
        List<Product> list = pageTu.getContent();
        responses.put("content",list); // chi tiết các phần tử được
        responses.put("currentPage",pageTu.getNumber() + 1);// trang hiện tại
        responses.put("totalItems",pageTu.getTotalElements());// tổng số phàn tử
        responses.put("totalPage",pageTu.getTotalPages()); // tổng só trang
        return responses;
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }


    // check và trừ sản phẩm
    public boolean checkDeductProduct (Integer productIdCheck, Integer quantity){
        Product product = productRepository.findById(productIdCheck).orElse(null);
        if (product == null){
            return false;
        }

        if (product.getQuantity() <0){
            return false;
        }

        Integer quantityCheck = product.getQuantity() - quantity;
        if (quantityCheck < 0){
            return false;
        }
        return deductProduct(productIdCheck,quantity);
    }

    public boolean deductProduct (Integer productIdCheck, Integer quantity){
        try{
            Product product = productRepository.findById(productIdCheck).orElse(null);
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

}
