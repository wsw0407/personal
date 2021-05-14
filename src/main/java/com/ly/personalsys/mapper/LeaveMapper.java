package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> getListPages(Page<Leave> page, @Param("content")String content);

    List<Leave> getListByEmpId(@Param("empId")Integer empId, @Param("content")String content);


}
