package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.personalsys.entity.Employee;
import com.ly.personalsys.mapper.EmployeeMapper;
import com.ly.personalsys.service.EmployeeService;
import com.ly.personalsys.vo.EmployeeVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
@Service
public class EmployeeServiceImp extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


    public Page<EmployeeVo> getEmpList(Page<EmployeeVo> page,String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

      return page.setRecords(this.baseMapper.getEmpList(page,content));
    }

    @Override
    public EmployeeVo getEmpById(Integer empId) {
        return this.baseMapper.getEmpById(empId);
    }

    @Override
    public Integer insertEmpResultId(Employee employee) {
        return this.baseMapper.insertEmpResultId(employee);
    }

}
