package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Employee;
import com.ly.personalsys.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

/*    @Select("SELECT emp.*,u.loginname loginname,d.name deptname,j.name jobname,edu.name educationname\n" +
            "        FROM employee_inf emp,user_inf u,dept_inf d,job_inf j,education_inf edu\n" +
            "        WHERE emp.user_id = u.id\n" +
            "        AND emp.dept_id = d.id\n" +
            "        AND emp.job_id = j.id\n" +
            "        AND emp.education_id = edu.id")*/
    List<EmployeeVo> getEmpList(Page<EmployeeVo> page, @Param("content")String content);

    EmployeeVo  getEmpById(@Param("empId")Integer empId);

    Integer  insertEmpResultId(Employee employee);

}
