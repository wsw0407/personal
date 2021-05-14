package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Train;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
public interface TrainService extends IService<Train> {

    Page<Train> getListPages(Page<Train> page, String content);

    List<Train> getTrainByEmpId(Integer empId);
}
