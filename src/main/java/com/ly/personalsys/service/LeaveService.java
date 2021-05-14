package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Leave;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
public interface LeaveService extends IService<Leave> {

    Page<Leave> getListPages(Page<Leave> page, String content);

    List<Leave> getListByEmpId(Integer empId, String content);

}
