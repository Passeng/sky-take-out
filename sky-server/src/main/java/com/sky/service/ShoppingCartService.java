package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 新增购物车
     *
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);


    /**
     * 查询购物车信息
     *
     * @return
     */
    List<ShoppingCart> showShoppingCartList();

    /**
     * 清空购物车
     *
     * @return
     */
    void cleanShoppingCart();
}
