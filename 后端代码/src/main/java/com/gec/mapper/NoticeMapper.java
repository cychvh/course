package com.gec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【notice】的数据库操作Mapper
* @createDate 2024-11-12 21:07:04
* @Entity com.gec.entity.Notice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




