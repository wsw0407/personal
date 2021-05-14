package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Traindata;
import com.ly.personalsys.mapper.TraindataMapper;
import com.ly.personalsys.service.TraindataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
@Service
public class TraindataServiceImp extends ServiceImpl<TraindataMapper, Traindata> implements TraindataService {

    @Override
    public Page<Traindata> getListPages(Page<Traindata> page, String content) {
        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getListPages(page,content));
    }
}
