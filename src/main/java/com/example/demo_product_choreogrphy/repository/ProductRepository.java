package com.example.demo_product_choreogrphy.repository;

import com.example.demo_product_choreogrphy.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findByName(String name);
    Page<Product> findByNameContaining(Pageable pageable, String name);
    Page<Product> findAll(Pageable pageable);
}
