package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
public interface NoticeService extends IService<Notice> {

    Page<Notice> getNoticeList(Page<Notice> page, String content);


}
