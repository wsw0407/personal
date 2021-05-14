package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.personalsys.entity.Train;
import com.ly.personalsys.mapper.TrainMapper;
import com.ly.personalsys.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
@Service
public class TrainServiceImp extends ServiceImpl<TrainMapper, Train> implements TrainService {

    @Override
    public Page<Train> getListPages(Page<Train> page, String content) {
        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getListPages(page,content));
    }

    @Override
    public List<Train> getTrainByEmpId(Integer empId) {
        return this.baseMapper.getTrainByEmpId(empId);
    }
}
