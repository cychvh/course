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
   @Update("UPDATE user " +
           "SET username = #{username}, " +
           "    password = #{password}, " +
           "    age = #{age}, " +
           "    email = #{email} " +  // 根据你的User实体字段补充
           "WHERE id = #{id}")  // 假设主键字段为id
   int updateByPrimaryKey(User record);
}




