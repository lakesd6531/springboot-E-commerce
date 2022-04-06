package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.ProductRepository;
import com.mark.springbootmall.dto.ProductDTO;
import com.mark.springbootmall.dto.ProductRequest;
import com.mark.springbootmall.model.ProductEntity;
import com.mark.util.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList.stream()
            .map(entity -> ModelMapperUtils.map(entity, ProductDTO.class))
            .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Integer productId) {
        ProductEntity entity = productRepository.findById(productId).orElse(null);
        if (entity == null) {
            return null;
        }
        return ModelMapperUtils.map(entity, ProductDTO.class);
    }

    @Transactional
    public Integer createProduct(ProductRequest productRequest) {
        ProductEntity entity = convertToEntity(productRequest);
        return productRepository.save(entity).getProductId();
    }

    @Transactional
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        ProductEntity entity = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("資料庫中找不到對應的產品"));

        setProductEntity(entity, productRequest);
        productRepository.save(entity);
    }

    @Transactional
    public void deleteProductById(Integer productId) {
        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
        }
    }

    private ProductEntity convertToEntity(ProductRequest productRequest) {
        return setProductEntity(new ProductEntity(), productRequest);
    }

    private ProductEntity setProductEntity(ProductEntity entity, ProductRequest productRequest) {
        entity.setProductName(productRequest.getProductName());
        entity.setCategory(productRequest.getCategory());
        entity.setImageUrl(productRequest.getImageUrl());
        entity.setPrice(productRequest.getPrice());
        entity.setStock(productRequest.getStock());
        return entity;
    }
}
