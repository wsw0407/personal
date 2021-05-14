package com.ly.personalsys.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> getNoticeList(Page<Notice> page, @Param("content")String content);


}
