package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.OrderDao;
import com.mark.springbootmall.dao.ProductDao;
import com.mark.springbootmall.dto.BuyItem;
import com.mark.springbootmall.dto.CreateOrderRequest;
import com.mark.springbootmall.model.OrderItem;
import com.mark.springbootmall.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    private final ProductDao productDao;

    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest){
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
