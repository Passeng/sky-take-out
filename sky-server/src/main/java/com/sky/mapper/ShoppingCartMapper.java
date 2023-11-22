package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.ShoppingCart;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 动态查询购物车列表
     *
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据id更新数量
     *
     * @param cart
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart cart);

    /**
     * 添加购物车
     *
     * @param shoppingCart
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into shopping_cart(name,image,user_id,dish_id,setmeal_id,dish_flavor,number,amount,create_time) " +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     *
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void cleanShoppingCartByUserId(Long userId);

    /**
     * 批量插入购物车数据
     *
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
