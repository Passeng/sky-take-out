package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

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

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    void editDish(DishDTO dishDTO);

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    DishVO queryByid(Long id);

    /**
     * 菜品启售停售
     *
     * @param id
     * @param status
     */
    void dishStartOrStop(Long id, Integer status);

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> getBycategoryId(Long categoryId);
}
