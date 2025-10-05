package com.gec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.entity.User;
import com.gec.req.UserQueryReq;
import com.gec.resp.UserResp;
import com.gec.util.Result;
import com.github.pagehelper.PageInfo;

import javax.validation.Valid;
import java.util.List;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2024-11-12 21:07:04
*/
public interface UserService extends IService<User> {

    UserResp login(String userId, String password);


    PageInfo<User> findTeacher(@Valid UserQueryReq req);
    Result saveOrUpdateUser(User user);
    Result deleteUser(String id);

    PageInfo<User> findDirector(@Valid UserQueryReq req);
    List<User> queryTeacher();

    Result upPwd(User user);

    // updatePassword();
}
