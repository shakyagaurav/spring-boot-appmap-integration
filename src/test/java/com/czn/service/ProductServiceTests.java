package com.czn.service;

import com.czn.entity.Product;
import com.czn.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should fetch all the products using pagination")
    void testGetProducts_withPagination() {
        List<Product> productList = Arrays.asList(Product.builder().id(1L).name("Test-Product 1").price(1999d).qty(9).build(),
                Product.builder().id(2L).name("Test-Product 2").price(2999d).qty(99).build());
        Page<Product> pageProduct = new PageImpl<>(productList);

        when(productRepository.findAll(any(PageRequest.class))).thenReturn(pageProduct);

        List<Product> result = productService.getProductsPagination(0);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);

        verify(productRepository, times(1)).findAll(any(PageRequest.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should fetch all the products without pagination")
    void testGetProducts_withoutPagination() {
        List<Product> productList = Arrays.asList(Product.builder().id(1L).name("Test-Product 1").price(1999d).qty(9).build(),
                Product.builder().id(2L).name("Test-Product 2").price(2999d).qty(99).build());

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);

        verify(productRepository, times(1)).findAll();
    }

    @DisplayName("Should save product")
    @Test
    public void testSaveProduct_willReturnNewProduct(){
        Product product = Product.builder().id(1L).name("Test-Product").price(2999d).qty(99).build();

        when(productRepository.save(product)).thenReturn(product);

        Product newProduct = productService.createProduct(product);

        assertThat(Objects.requireNonNull(newProduct.getId())).isEqualTo(1L);

        verify(productRepository, times(1)).save(product);
    }
}
