package com.czn.resource;

import com.czn.entity.Product;
import com.czn.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductResourceTests {

    @InjectMocks
    private ProductResource productResource;

    @Mock
    private ProductService productService;

    @DisplayName("Test product creation")
    @Test
    public void createProduct_givenValidBody_willReturnNewProduct(){
        Product product = Product.builder().id(1L).name("Test-Product").price(2999d).qty(99).build();

        when(productService.createProduct(product)).thenReturn(product);

        ResponseEntity<Product> newProduct = productResource.createProduct(product);

        assertThat(newProduct.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(Objects.requireNonNull(newProduct.getBody()).getId()).isEqualTo(1L);
    }

    @DisplayName("Test Get all products without pagination")
    @Test
    public void getALlProducts_withoutPagination_willReturnProducts(){

        List<Product> productList = Collections.singletonList(Product.builder().id(1L).name("Test-Product").price(2999d).qty(99).build());

        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> result = productResource.getAllProducts();

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().size()).isEqualTo(1);
        assertThat(result.getBody().getFirst().getId()).isEqualTo(1L);

        verify(productService, times(1)).getAllProducts();
    }

    @DisplayName("Test Get all products with pagination")
    @Test
    public void getProducts_withPagination_willReturnProducts() {


        List<Product> productList = Arrays.asList(Product.builder().id(1L).name("Product 1").price(1999d).qty(10).build(),
                Product.builder().id(2L).name("Product 2").price(2999d).qty(20).build());

        when(productService.getProductsPagination(0)).thenReturn(productList);

        ResponseEntity<List<Product>> result = productResource.getAllProducts(0);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).hasSize(2);
        assertThat(result.getBody().getFirst().getId()).isEqualTo(1L);

        verify(productService, times(1)).getProductsPagination(0);
    }
}
