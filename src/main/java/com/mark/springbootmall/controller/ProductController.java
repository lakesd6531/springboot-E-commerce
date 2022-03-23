package com.mark.springbootmall.controller;

import com.mark.springbootmall.dto.ProductDTO;
import com.mark.springbootmall.dto.ProductRequest;
import com.mark.springbootmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer productId) {
        ProductDTO dto = productService.getProductById(productId);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        ProductDTO productDTO = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable Integer productId,
        @RequestBody @Valid ProductRequest productRequest) {

        // 檢查 product 是否存在
        if(productService.getProductById(productId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改商品的數據
        productService.updateProduct(productId, productRequest);

        ProductDTO updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}
