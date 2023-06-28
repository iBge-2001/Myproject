package com.ibge.studentinformationmanage.service.impl;

import com.ibge.studentinformationmanage.entity.Class;
import com.ibge.studentinformationmanage.mapper.ClassMapper;
import com.ibge.studentinformationmanage.service.ClassService;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Override
    public Integer getCountByClassId(Integer classid) {
        return classMapper.getCountByClassId(classid);
    }

    @Override
    public String getClassCount() {
        return classMapper.getClassCount();
    }

    @Override
    public List<String> getClassname() {
        return classMapper.getClassname();
    }

    @Override
    public String getAvgCount() {
        return classMapper.getAvgCount();
    }

}
