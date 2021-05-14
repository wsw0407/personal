package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Salary;
import com.ly.personalsys.mapper.SalaryMapper;
import com.ly.personalsys.service.SalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
@Service
public class SalaryServiceImp extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {

    @Override
    public Page<Salary> getListPages(Page<Salary> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getListPages(page,content));
    }

    @Override
    public Salary getSalaryByEmpId(Integer empId) {
        return this.baseMapper.getSalaryByEmpId(empId);
    }
}
