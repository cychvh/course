package com.gec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.entity.Role;
import com.gec.req.RoleReq;
import com.gec.util.Result;

import java.util.List;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Service
* @createDate 2024-11-12 21:07:04
*/
public interface RoleService extends IService<Role> {

    List<RoleReq> getRoleAndPower();

    //Result saveOrUpdateCourse(Role role);

    Result editRole(RoleReq roleReq);
}
