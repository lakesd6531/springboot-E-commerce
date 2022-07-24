package com.mark.springbootmall.model;

import com.mark.springbootmall.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private Integer productId;

    private String productName;

    private ProductCategory category;

    private String imageUrl;

    private int price;

    private int stock;

    private String description;

    private Date createdDate;

    private Date lastModifiedDate;

}
