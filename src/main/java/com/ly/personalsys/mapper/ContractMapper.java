package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> getListPages(Page<Contract> page, @Param("content")String content);

    Contract getContractByEmpId(@Param("empId")Integer empId);

}
