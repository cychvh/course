package com.gec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.entity.Power;
import com.gec.entity.User;
import com.gec.mapper.PowerMapper;
import com.gec.mapper.UserMapper;
import com.gec.req.UserQueryReq;
import com.gec.resp.UserResp;
import com.gec.service.UserService;
import com.gec.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-12 21:07:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    PowerMapper powerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResp login(String userId, String password) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq( "id",userId);
        queryWrapper.eq( "password",password);
        User user=baseMapper.selectOne(queryWrapper);

        if(user!=null){
            UserResp userResp = CopyUtil.copy(user, UserResp.class);
            Power power = powerMapper.selectById(userResp.getType());
            userResp.setPower(power.getPower());
            return userResp;
        }else{
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_ERROR);
        }
    }
    @Override
    public PageInfo<User>findTeacher(@Valid UserQueryReq req){
        //调用业务逻辑代码去查询出对应的教师数据
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //因为我们教师和主任是放在一起的 使用type来进行区分1老师2主任
        //设置查询条件
        wrapper.eq("type",req.getType());
        //设置搜索条件，如果有的话
        if(req.getName() != null){
            wrapper.like("name","%" + req.getName() + "%");
        }
        //设置分页查询的参数信息
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        //调用数据库访问层代码查询
        List<User> users = baseMapper.selectList(wrapper);
        //设置分页信息
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
    public Result saveOrUpdateUser(User user){
        //通过编号进行查询是要修改还是添加
        User selectById = baseMapper.selectById(user.getId());
        //这个id对应的记录不存是添加
        if(selectById == null){
            int insert = baseMapper.insert(user);
            if(insert>0){
                return Result.success();
            }else{
                return Result.error("4001","添加失败");
            }

        }else{
            int update = baseMapper.updateById(user);
            if(update>0){
                return Result.success();
            }else{
                return Result.error("4002","修改失败");
            }
        }
    }
    public Result deleteUser(String id){
        int delete = baseMapper.deleteById(id);
        if(delete >0){
            return Result.success();
        }
        else{
            return Result.error("4003","删除失败");
        }
    }

    @Override
    public PageInfo<User> findDirector(UserQueryReq req) {
        //调用业务逻辑代码去查询出对应的教师数据
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //因为我们教师和主任是放在一起的 使用type来进行区分1老师2主任
        //设置查询条件
        wrapper.eq("type",req.getType());
        //设置搜索条件，如果有的话
        if(req.getName() != null){
            wrapper.like("name","%" + req.getName() + "%");
        }
        //设置分页查询的参数信息
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        //调用数据库访问层代码查询
        List<User> users = baseMapper.selectList(wrapper);
        //设置分页信息
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<User> queryTeacher() {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type",1);
        List<User> users = baseMapper.selectList(queryWrapper);
        return users;
    }

    @Override
    public Result upPwd(User user) {
        System.out.println(user);
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        updateWrapper.set("password",user.getPassword());
        int i = userMapper.update(user,updateWrapper);
        if(i>0){
            return Result.success();
        }else{
            return Result.error("40010","修改失败");
        }
    }
}




