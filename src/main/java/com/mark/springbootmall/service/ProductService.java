package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.ProductRepository;
import com.mark.springbootmall.dto.ProductDTO;
import com.mark.springbootmall.dto.ProductRequest;
import com.mark.springbootmall.model.ProductEntity;
import com.mark.util.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO getProductById(Integer productId) {
        ProductEntity entity = productRepository.findById(productId).orElse(null);
        if (entity == null) {
            return null;
        }
        return ModelMapperUtils.map(entity, ProductDTO.class);
    }

    public Integer createProduct(ProductRequest productRequest) {
        ProductEntity entity = convertToEntity(productRequest);
        return productRepository.save(entity).getProductId();
    }

    private ProductEntity convertToEntity(ProductRequest productRequest) {
        ProductEntity entity = new ProductEntity();
        entity.setProductName(productRequest.getProductName());
        entity.setCategory(productRequest.getCategory());
        entity.setImageUrl(productRequest.getImageUrl());
        entity.setPrice(productRequest.getPrice());
        entity.setStock(productRequest.getStock());
        return entity;
    }
}
