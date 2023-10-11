package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    @Override
    @Transactional
    public void saveDish(DishDTO dishDTO) {
        //组装dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        if (dish.getStatus() == null) {
            dish.setStatus(StatusConstant.ENABLE);
        }

        //调用mapper保存dish
        dishMapper.saveDish(dish);

        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult queryDishPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.queryDishPage(dishPageQueryDTO);
        long total = page.getTotal();
        List<DishVO> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    @Override
    public void deleteDishBatch(List<Long> ids) {
        //判断被删除的菜品中是否为起售状态 有则不能删除
        Integer count = dishMapper.countEnablesByIds(ids);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
        }

        //判断被删除的菜品中有没有被套餐关联 有则不能删除
        count = setmealMapper.countByDishIds(ids);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
        }

        //删除菜品
        dishMapper.deleteDishBatch(ids);

        //删除菜品关联的口味数据
        dishFlavorMapper.deleteBatchByIds(ids);
    }
}
