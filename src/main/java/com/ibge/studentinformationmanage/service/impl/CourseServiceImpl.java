package com.ibge.studentinformationmanage.service.impl;

import com.ibge.studentinformationmanage.entity.Course;
import com.ibge.studentinformationmanage.mapper.CourseMapper;
import com.ibge.studentinformationmanage.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Boolean updateTgrade(Course course) {
        return courseMapper.updateTgrade(course)>0;
    }
}
