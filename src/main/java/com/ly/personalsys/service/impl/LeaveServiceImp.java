package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Leave;
import com.ly.personalsys.mapper.LeaveMapper;
import com.ly.personalsys.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
@Service
public class LeaveServiceImp extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Override
    public Page<Leave> getListPages(Page<Leave> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getListPages(page,content));
    }

    @Override
    public List<Leave> getListByEmpId(Integer empId, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return this.baseMapper.getListByEmpId(empId,content);
    }
}
