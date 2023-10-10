package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
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
}
