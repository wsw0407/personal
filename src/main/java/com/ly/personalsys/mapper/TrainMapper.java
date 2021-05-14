package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Train;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
public interface TrainMapper extends BaseMapper<Train> {

    List<Train> getListPages(Page<Train> page, @Param("content")String content);

    List<Train> getTrainByEmpId(@Param("empId")Integer empId);

}
