package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

	/**
	 * 员工登录
	 *
	 * @param employeeLoginDTO
	 * @return
	 */
	Employee login(EmployeeLoginDTO employeeLoginDTO);

	/**
	 * 新增员工
	 *
	 * @param employeeDTO
	 * @return
	 */
	void saveEmployee(EmployeeDTO employeeDTO);

	/**
	 * 分页查询
	 *
	 * @param employeePageQueryDTO
	 * @return
	 */
	PageResult queryEmployeePage(EmployeePageQueryDTO employeePageQueryDTO);

	/**
	 * 员工账号启用禁用
	 *
	 * @param status
	 * @param id
	 */
	void statusStartOrStop(Integer status, Long id);

	/**
	 * 修改员工信息
	 *
	 * @param employeeDTO
	 */
	void editEmployeeInfo(EmployeeDTO employeeDTO);

	/**
	 * 根据id查询员工信息
	 *
	 * @param id
	 * @return
	 */
	Employee getById(Long id);
}
