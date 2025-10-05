package com.gec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.entity.Role;
import com.gec.req.RoleReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Mapper
* @createDate 2024-11-12 21:07:04
* @Entity com.gec.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from role,power where role.powerid = power.roleid")
    List<RoleReq>getRolesAndPower();
    @Update("UPDATE role\n" +
            "JOIN power ON role.powerid = power.roleid\n" +
            "SET role.rolename = #{roleName}, power.power = #{powerValue}\n" +
            "WHERE role.powerid = #{powerId};")
    int updateRoleAndPower(@Param("roleName") String roleName,
                           @Param("powerValue") String powerValue,
                           @Param("powerId") String powerId);

}




