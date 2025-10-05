package com.gec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.entity.Role;
import com.gec.mapper.PowerMapper;
import com.gec.mapper.RoleMapper;
import com.gec.req.RoleReq;
import com.gec.service.RoleService;
import com.gec.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-11-12 21:07:04
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<RoleReq> getRoleAndPower() {
        List<RoleReq> rolesAndPower = baseMapper.getRolesAndPower();
        return rolesAndPower;
    }



    @Override
    public Result editRole(RoleReq roleReq) {
        int index = baseMapper.updateRoleAndPower(roleReq.getRolename(),roleReq.getPower(),roleReq.getPowerid());
        if (index < 0){
            return Result.error( "40020,", "修改失败，角色信息不存在！");
        }
        return Result.success();
    }
}



