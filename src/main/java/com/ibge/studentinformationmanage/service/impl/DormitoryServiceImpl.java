package com.ibge.studentinformationmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.DormitoryDto;
import com.ibge.studentinformationmanage.entity.Class;
import com.ibge.studentinformationmanage.entity.Dormitory;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.mapper.DormitoryMapper;
import com.ibge.studentinformationmanage.service.ClassService;
import com.ibge.studentinformationmanage.service.DormitoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibge.studentinformationmanage.service.StudentService;
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
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Override
    public IPage<DormitoryDto> findByPage(Integer page, Integer pageSize, Long id) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getSid,id);
        //拿到班级id
        Integer classid = studentService.getOne(lambdaQueryWrapper).getClassid();
        classLambdaQueryWrapper.eq(Class::getClassid,classid);
        //拿到班级名称
        String classname = classService.getOne(classLambdaQueryWrapper).getClassname();
        Page<DormitoryDto> pageInfo = new Page<>(page, pageSize);
        IPage<DormitoryDto> dormitoryMapperByPage = dormitoryMapper.findByPage(pageInfo, classname);
        return dormitoryMapperByPage;
    }
}
