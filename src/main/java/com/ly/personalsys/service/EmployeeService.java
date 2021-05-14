package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Employee;
import com.ly.personalsys.vo.EmployeeVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
public interface EmployeeService extends IService<Employee> {

     Page<EmployeeVo> getEmpList(Page<EmployeeVo> page,String content);

     EmployeeVo  getEmpById(Integer empId);

     Integer  insertEmpResultId(Employee employee);
}
