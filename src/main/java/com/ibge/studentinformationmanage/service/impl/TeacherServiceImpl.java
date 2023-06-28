package com.ibge.studentinformationmanage.service.impl;

import com.ibge.studentinformationmanage.entity.Teacher;
import com.ibge.studentinformationmanage.mapper.TeacherMapper;
import com.ibge.studentinformationmanage.service.TeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Integer getTrgadeByUser(String user) {
        return teacherMapper.getTrgadeByUser(user);
    }
}
