package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    void saveDish(DishDTO dishDTO);

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult queryDishPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    void deleteDishBatch(List<Long> ids);
}
