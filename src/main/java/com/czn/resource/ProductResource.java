package com.czn.resource;


import com.czn.entity.Product;
import com.czn.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("getAllProducts method invoked");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("getAllProducts method invoked for page: {}", page);
        return new ResponseEntity<>(productService.getProductsPagination(page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product  product) {
        log.info("createProduct method invoked for request data: {}", product);
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/insecure-compare")
    public ResponseEntity<Boolean> insecureCompare() {
        log.info("insecureCompare method invoked");
        return new ResponseEntity<>("czn"=="abc", HttpStatus.OK);
    }

}
