package com.gec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.entity.Notice;
import com.gec.util.Result;

import java.util.List;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Service
* @createDate 2024-11-12 21:07:04
*/
public interface NoticeService extends IService<Notice> {

    List<Notice> queryNote();

    Result saveOrUpdateNote(Notice notice);
    boolean deleteNote(String id);
}
