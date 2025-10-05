package com.gec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.entity.Course;
import com.gec.mapper.CourseMapper;
import com.gec.req.CourseReq;
import com.gec.service.CourseService;
import com.gec.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-11-12 21:07:04
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Override
    public List<Course> queryCourse() {
        List<Course> courseList = baseMapper.selectList(null);
        return courseList;
    }
    @Override
    public Result saveOrUpdateCourse(Course course){
        if(StringUtils.isEmpty(course.getId())){
            course.setStatus("1");
            course.setId(String.valueOf(Math.random()).substring(2,10));
            int insert = baseMapper.insert(course);
            if(insert > 0){
                return Result.success();
            }else {
                return Result.error("40004","添加失败");
            }
        }else{
            int update = baseMapper.updateById(course);
            if(update > 0){
                return Result.success();
            }else{
                return Result.error("40004","修改失败");
            }
        }
    }

    @Override
    public Result arraning(CourseReq req) {
        Course course = baseMapper.selectById(req.getId());
        String[] date1 = req.getDate1();
        String[] date2 = req.getDate2();
       course.setCourseroom(StringUtils.join(req.getCourseroom(),","));
       course.setCoursedate((StringUtils.join(date1,","))+StringUtils.join(date2,","));
       int update = baseMapper.updateById(course);
       if(update > 0){
           return Result.success();
       }else{
           return  Result.error("40007" ,"排课失败");
       }
    }
}




