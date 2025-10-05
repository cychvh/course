package com.gec.controller;

import com.gec.req.RoleReq;
import com.gec.service.RoleService;
import com.gec.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
   @GetMapping( "/queryRole")
   public Result queryRole(){
       List<RoleReq> roleAndPower = roleService.getRoleAndPower();
       return Result.success(roleAndPower);
   }
    @PutMapping("/editRole")
    public Result editRole(@RequestBody RoleReq roleReq) {
        Result result = roleService.editRole(roleReq);
        return Result.success();
    }
}
