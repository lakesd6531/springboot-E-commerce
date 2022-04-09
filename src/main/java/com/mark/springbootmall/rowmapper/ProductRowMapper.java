package com.mark.springbootmall.rowmapper;

import com.mark.springbootmall.constant.ProductCategory;
import com.mark.springbootmall.dto.ProductDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductDTO> {
    @Override
    public ProductDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductDTO product = new ProductDTO();
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreatedDate(resultSet.getTimestamp("created_date"));
        product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        return product;
    }
}
