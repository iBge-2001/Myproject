package com.ibge.studentinformationmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.StudentDto;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.mapper.StudentMapper;
import com.ibge.studentinformationmanage.service.ClassService;
import com.ibge.studentinformationmanage.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
