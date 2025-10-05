package com.gec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.entity.Notice;
import com.gec.mapper.NoticeMapper;
import com.gec.service.NoticeService;
import com.gec.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2024-11-12 21:07:04
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

    @Override
    @Cacheable(value = "notices",key = "all")
    public List<Notice> queryNote() {
        List<Notice> noticeList = baseMapper.selectList(null);
        return noticeList;
    }

    @Override
    @CacheEvict(value = "notices",key = "all")
    public Result saveOrUpdateNote(Notice notice) {
        if(StringUtils.isEmpty(notice.getId())){
            notice.setId(String.valueOf(Math.random()).substring(2,10));
            int insert = baseMapper.insert(notice);
            if(insert > 0){
                return Result.success();
            }else {
                return Result.error("40008","添加失败");
            }
        }else{
            int update = baseMapper.updateById(notice);
            if(update > 0){
                return Result.success();
            }else{
                return Result.error("40008","修改失败");
            }
        }
    }

    @Override
    @CacheEvict(value = "notices",key = "all")
    public boolean deleteNote(String id) {
        return baseMapper.deleteById(id) > 0;
    }
}




