package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.personalsys.entity.Notice;
import com.ly.personalsys.mapper.NoticeMapper;
import com.ly.personalsys.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
@Service
public class NoticeServiceImp extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    @Override
    public Page<Notice> getNoticeList(Page<Notice> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getNoticeList(page,content));
    }
}
