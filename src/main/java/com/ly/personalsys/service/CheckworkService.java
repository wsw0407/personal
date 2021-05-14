package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Checkwork;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
public interface CheckworkService extends IService<Checkwork> {

    Page<Checkwork> getCheckworkList(Page<Checkwork> page, String content);

    List<Checkwork> getChecListByEmpId(Integer empId, String content);

}
