package com.gec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-11-12 21:07:04
* @Entity com.gec.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

   int updateByPrimaryKey(User record);
}




