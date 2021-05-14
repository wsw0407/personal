package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Salary;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
public interface SalaryService extends IService<Salary> {

    Page<Salary> getListPages(Page<Salary> page, String content);

    Salary getSalaryByEmpId(Integer empId);
}
