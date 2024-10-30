package com.czn.service;

import com.czn.entity.Product;
import com.czn.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
//        String id = String.format("%04d", new Random().nextInt(10000));
//        return productRepository.save(Product.builder().name("T-Shirt "+id).qty(10).price(Double.valueOf(id)).build());

        return productRepository.save(product);
    }

    public List<Product> getProductsPagination(int page) {
        Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page, 10, Sort.Direction.DESC, "price"));
        return pageProduct.getContent();
    }
}
