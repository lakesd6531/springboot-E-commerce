package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.ProductRepository;
import com.mark.springbootmall.dto.ProductDTO;
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
}
