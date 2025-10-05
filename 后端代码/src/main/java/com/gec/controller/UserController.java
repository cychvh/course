package com.gec.controller;

import com.alibaba.fastjson.JSONObject;
import com.gec.entity.User;
import com.gec.req.UserQueryReq;
import com.gec.resp.UserResp;
import com.gec.service.UserService;
import com.gec.util.Constans;
import com.gec.util.Result;
import com.gec.util.SnowFlake;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    SnowFlake snowFlake;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public Result<User> login(String userId, String password) {
        System.out.println("账号:"+userId);
        System.out.println("密码"+password);
        UserResp userResp = userService.login(userId, password);
        Long token = snowFlake.nextId();
        userResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(),
                JSONObject.toJSONString(userResp),
                60 * 60 * 24, TimeUnit.SECONDS);
        return Result.success(userResp);
    }

    @GetMapping( "/loginOut/{token}")
    public Result loginOut(@PathVariable String token) {
        redisTemplate.delete(token);
        return Result.success();
    }
    @GetMapping("/findTeacher")
    public Result findTeacher(@Valid UserQueryReq req) {
        System.out.println(req);
        req.setType(Constans.TEACHER);
        PageInfo<User> pageInfo = userService.findTeacher(req);
        return Result.success(pageInfo);
    }
    @PostMapping("/saveUser")
    //
    public Result saveUser(@RequestBody User user){
        //@RequestBody告诉前端提交过来的参数是json格式的需要进行转化
        System.out.println(user);
        Result result = userService.saveOrUpdateUser(user);
        return Result.success();
    }
    @DeleteMapping("/delUser/{id}")
    public Result delUser(@PathVariable String id){
        Result result = userService.deleteUser(id);
        return  result;
    }
    @GetMapping("/findDirector")
    public Result findDirector(@Valid UserQueryReq req) {
        System.out.println(req);
        req.setType(Constans.DIRECTOR);
        PageInfo<User> pageInfo = userService.findDirector(req);
        return Result.success(pageInfo);
    }
    @PostMapping("/upPwd")
    public Result upPwd(String oldPwd, String newPwd, HttpServletRequest request) {
        System.out.println(oldPwd+"[["+newPwd);
        String token = request.getHeader("token");
        User user = JSONObject.parseObject((String) redisTemplate.opsForValue().get(token), User.class);
        System.out.println("--"+user);
        if(!user.getPassword().equals(oldPwd)){
            return Result.success("旧密码不正确");
        }else{
            user.setPassword(newPwd);
            userService.upPwd(user);
            return Result.success("修改密码成功");
        }
    }

}
