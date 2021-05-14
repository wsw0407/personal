package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    List<Salary> getListPages(Page<Salary> page, @Param("content")String content);

    Salary getSalaryByEmpId(@Param("empId")Integer empId);

}
