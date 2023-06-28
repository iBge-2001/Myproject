package com.ibge.studentinformationmanage.service;

import com.ibge.studentinformationmanage.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
public interface CourseService extends IService<Course> {
    public Boolean updateTgrade(Course course);
}
