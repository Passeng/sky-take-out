package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(tags = "菜品相关接口")
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("新增菜品")
    @PostMapping
    public Result saveDish(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveDish(dishDTO);

        //清理缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);
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

        //清理缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    @ApiOperation("根据id查询菜品")
    @GetMapping("{id}")
    public Result queryByid(@PathVariable Long id) {
        log.info("根据id查询菜品:{}", id);
        DishVO dishVO = dishService.queryByid(id);
        return Result.success(dishVO);
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> getBycategoryId(Long categoryId) {
        log.info("根据套餐id查询菜品{}:", categoryId);
        List<Dish> list = dishService.getBycategoryId(categoryId);
        return Result.success(list);
    }

    @ApiOperation("修改菜品")
    @PutMapping
    public Result editDish(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品:{}", dishDTO);
        dishService.editDish(dishDTO);

        //清理缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    @ApiOperation("菜品开售停售")
    @PostMapping("/status/{status}")
    public Result dishStartOrStop(@PathVariable Integer status, Long id) {
        log.info("菜品开售停售:{},{}", id, status);
        dishService.dishStartOrStop(id, status);

        //清理缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 清理缓存数据
     *
     * @param pattern
     */
    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
