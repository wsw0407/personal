package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Contract;
import com.ly.personalsys.mapper.ContractMapper;
import com.ly.personalsys.service.ContractService;
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
public class ContractServiceImp extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    @Override
    public Page<Contract> getListPages(Page<Contract> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getListPages(page,content));

    }

    @Override
    public Contract getContractByEmpId(Integer empId) {
        return this.baseMapper.getContractByEmpId(empId);
    }
}
