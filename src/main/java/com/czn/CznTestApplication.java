package com.czn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CznTestApplication {

//    public CznTestApplication(ProductService productService) {
//        this.productService = productService;
//    }

//    @Override
//	public void run(String[] args) throws Exception
//	{
//		IntStream.range(0, 20).forEach(
//				nbr -> productService.createProduct()
//		);
//
//		productService.getAllProducts().forEach(System.out::println);
//	}

	public static void main(String[] args) {
		SpringApplication.run(CznTestApplication.class, args);
	}

}
