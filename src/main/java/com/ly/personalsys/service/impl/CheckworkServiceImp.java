package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Checkwork;
import com.ly.personalsys.mapper.CheckworkMapper;
import com.ly.personalsys.service.CheckworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
@Service
public class CheckworkServiceImp extends ServiceImpl<CheckworkMapper, Checkwork> implements CheckworkService {

    @Override
    public Page<Checkwork> getCheckworkList(Page<Checkwork> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getCheckworkList(page,content));
    }

    @Override
    public List<Checkwork> getChecListByEmpId(Integer empId, String content) {
        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }
        return this.baseMapper.getChecListByEmpId(empId,content);
    }
}
