package com.mark.springbootmall.dao;

import com.mark.springbootmall.constant.ProductCategory;
import com.mark.springbootmall.dto.ProductDTO;
import com.mark.springbootmall.rowmapper.ProductRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProductDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ProductDTO> getProducts(ProductCategory category, String search) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
            "created_date, last_modified_date " +
            "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if (category != null) {
            sql = sql + " AND category = :category";
            map.put("category", category.name());
        }

        if (search != null) {
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + search + "%");
        }

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }
}
