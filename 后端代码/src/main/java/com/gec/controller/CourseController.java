package com.gec.controller;

import com.gec.entity.Course;
import com.gec.entity.User;
import com.gec.req.CourseReq;
import com.gec.service.CourseService;
import com.gec.service.UserService;
import com.gec.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @GetMapping("/queryTeacher")
    public Result queryTeacher(){
        List<User> users = userService.queryTeacher();
        return Result.success(users);
    }
    @GetMapping("/queryCourse")
    public Result queryCourse(){
       List<Course> courseList =  courseService.queryCourse();
       return Result.success(courseList);
    }
    @PostMapping("/saveCourse")
    public Result saveCourse(@RequestBody Course course){
        System.out.println(course);
        Result result = courseService.saveOrUpdateCourse(course);
        return Result.success(result);
    }
    @DeleteMapping("/delCourse/{id}")
    public Result delCourse(@PathVariable String id){
        if(courseService.removeById(id)){
            return Result.success();
        }else{
            return Result.error("40006","删除失败");
        }
    }
    @PostMapping("/arranging")
    public Result arranging(@RequestBody CourseReq req){
        System.out.println(req);
        Result result = courseService.arraning(req);
        return Result.success(result);
    }
}
