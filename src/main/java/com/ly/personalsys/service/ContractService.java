package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Contract;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
public interface ContractService extends IService<Contract> {

    Page<Contract> getListPages(Page<Contract> page, String content);

    Contract getContractByEmpId(Integer empId);
}
