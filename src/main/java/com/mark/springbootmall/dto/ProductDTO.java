package com.mark.springbootmall.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Integer productId;
    private String productName;
    private String category;
    private String imageUrl;
    private int price;
    private int stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}
