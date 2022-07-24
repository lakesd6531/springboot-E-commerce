package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.ProductDao;
import com.mark.springbootmall.dto.ProductQueryParams;
import com.mark.springbootmall.dto.ProductRequest;
import com.mark.springbootmall.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        List<Product> productList = productDao.getProducts(productQueryParams);
        return productList;
    }

    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Transactional
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Transactional
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Transactional
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    public Integer countProduct(ProductQueryParams productQueryParams){
        return productDao.countProduct(productQueryParams);
    }

}
