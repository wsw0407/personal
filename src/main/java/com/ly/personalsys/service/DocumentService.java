package com.ly.personalsys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.personalsys.entity.Document;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
public interface DocumentService extends IService<Document> {

    Page<Document> getDocumentList(Page<Document> page, String content);

}
