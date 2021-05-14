package com.ly.personalsys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.personalsys.entity.Document;
import com.ly.personalsys.mapper.DocumentMapper;
import com.ly.personalsys.service.DocumentService;
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
public class DocumentServiceImp extends ServiceImpl<DocumentMapper, Document> implements DocumentService {


    @Override
    public Page<Document> getDocumentList(Page<Document> page, String content) {

        if(content != null && !content.equals("")){
            content = "%" + content + "%";
        }

        return page.setRecords(this.baseMapper.getDocumentList(page,content));

    }
}
