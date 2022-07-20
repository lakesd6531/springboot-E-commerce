package com.mark.springbootmall.dao;

import com.mark.springbootmall.dto.ProductDTO;
import com.mark.springbootmall.dto.ProductQueryParams;
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

    public List<ProductDTO> getProducts(ProductQueryParams productQueryParams) {
        // WHERE 1=1 不會對查詢結果有任何的影響的
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
            "created_date, last_modified_date " +
            "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        /* WHERE 1=1 主要是想要讓下面的查詢條件自由地去拼接在上面SQL語法的後面
         如果 getCategory 不等於 null的話，整個sql語法就會變成
         SELECT... FROM product WHERE 1=1 AND category = :category
         (要特別注意 AND需保留空白)
         如果 getCategory 等於 null的話，sql語法就會維持原本的樣子
         SELECT... FROM product WHERE 1=1
         */

        // 查詢條件
        sql = addFilterSql(sql, map, productQueryParams);

        // 排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }

    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilterSql(sql, map, productQueryParams);

        return namedParameterJdbcTemplate.queryForObject(sql, map,Integer.class);
    }

    private String addFilterSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams){
        if (productQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if (productQueryParams.getSearch() != null) {
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        return sql;
    }
}
