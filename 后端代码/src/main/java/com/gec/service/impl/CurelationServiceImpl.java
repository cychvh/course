package com.gec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.entity.Course;
import com.gec.entity.Curelation;
import com.gec.mapper.CourseMapper;
import com.gec.mapper.CurelationMapper;
import com.gec.service.CurelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【curelation】的数据库操作Service实现
* @createDate 2024-11-12 21:07:04
*/
@Service
public class CurelationServiceImpl extends ServiceImpl<CurelationMapper, Curelation>
    implements CurelationService{
    @Autowired
    CourseMapper courseMapper;
    @Override
    public void listen(Curelation curelation) {
        String courseid = curelation.getCourseid();
        QueryWrapper<Curelation>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("courseid", courseid);
        Curelation curelations = baseMapper.selectOne(queryWrapper);
        if(curelations!=null){
            String Oldname = curelations.getName();

            String Newname = Oldname + "," + curelation.getName();
            curelations.setName(Newname);
            baseMapper.updateById(curelations);

        }else {
            curelation.setId(String.valueOf(Math.random()*100000000).substring(1,9));
            Course course = courseMapper.selectById(courseid);
            curelation.setType(course.getCoursename());
            baseMapper.insert(curelation);
        }
    }
}




