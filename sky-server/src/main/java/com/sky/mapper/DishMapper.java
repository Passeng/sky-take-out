package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 添加菜品
     *
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void saveDish(Dish dish);

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> queryDishPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 统计起售状态的菜品数量
     *
     * @param ids
     * @return
     */
    Integer countEnablesByIds(List<Long> ids);

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    void deleteDishBatch(List<Long> ids);
}
