package com.gec.service;

import com.gec.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.req.CourseReq;
import com.gec.util.Result;

import java.util.List;

/**
* @author Administrator
* @description 针对表【course】的数据库操作Service
* @createDate 2024-11-12 21:07:04
*/
public interface CourseService extends IService<Course> {


    List<Course> queryCourse();
    Result saveOrUpdateCourse(Course course);

    Result arraning(CourseReq req);
}
