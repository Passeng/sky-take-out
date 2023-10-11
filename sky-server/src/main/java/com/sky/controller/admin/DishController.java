package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜品相关接口")
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

	@Autowired
	private DishService dishService;

	@ApiOperation("新增菜品")
	@PostMapping
	public Result saveDish(@RequestBody DishDTO dishDTO) {
		log.info("新增菜品:{}", dishDTO);
		dishService.saveDish(dishDTO);
		return Result.success();
	}

	@ApiOperation("分页查询菜品")
	@GetMapping("/page")
	public Result queryDishPage(DishPageQueryDTO dishPageQueryDTO) {
		log.info("分页查询菜品:{}", dishPageQueryDTO);
		PageResult result = dishService.queryDishPage(dishPageQueryDTO);
		return Result.success(result);
	}

	@ApiOperation("批量删除菜品")
	@DeleteMapping
	public Result deleteDishBatch(@RequestParam List<Long> ids) {
		log.info("批量删除菜品:{}", ids);
		dishService.deleteDishBatch(ids);
		return Result.success();
	}

	@ApiOperation("根据id查询菜品")
	@GetMapping("{id}")
	public Result queryByid(@PathVariable Long id) {
		log.info("根据id查询菜品:{}", id);
		DishVO dishVO = dishService.queryByid(id);
		return Result.success(dishVO);
	}

	@ApiOperation("修改菜品")
	@PutMapping
	public Result editDish(@RequestBody DishDTO dishDTO) {
		log.info("修改菜品:{}", dishDTO);
		dishService.editDish(dishDTO);
		return Result.success();
	}

	@ApiOperation("菜品开售停售")
	@PostMapping("/status/{status}")
	public Result dishStartOrStop(@PathVariable Integer status, Long id) {
		log.info("菜品开售停售:{},{}", id, status);
		dishService.dishStartOrStop(id, status);
		return Result.success();
	}
}
