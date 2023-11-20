package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     *
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     *
     * @param outTradeNo
     * @return
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String outTradeNo);

    /**
     * 更新数据
     *
     * @param orders
     */
    void update(Orders orders);

    /**
     * 根据订单状态和下单时间查询订单
     *
     * @param status
     * @param orderTime
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time = #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 通过id获取订单
     *
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id};")
    Orders getById(Long id);

    /**
     * 通过动态统计营业额数据
     *
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 通过动态统计订单数据
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
