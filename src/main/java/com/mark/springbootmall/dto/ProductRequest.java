package com.mark.springbootmall.dto;

import com.mark.springbootmall.constant.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private int price;

    @NotNull
    private int stock;

    private String description;
}
