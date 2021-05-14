package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Traindata;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
public interface TraindataService extends IService<Traindata> {

    Page<Traindata> getListPages(Page<Traindata> page, String content);

}
