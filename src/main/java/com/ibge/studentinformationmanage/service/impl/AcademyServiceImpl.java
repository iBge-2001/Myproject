package com.ibge.studentinformationmanage.service.impl;

import com.ibge.studentinformationmanage.entity.Academy;
import com.ibge.studentinformationmanage.mapper.AcademyMapper;
import com.ibge.studentinformationmanage.service.AcademyService;
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
public class AcademyServiceImpl extends ServiceImpl<AcademyMapper, Academy> implements AcademyService {
    @Autowired AcademyMapper academyMapper;
    @Override
    public List<String> getAcademy() {
        return academyMapper.getAcademy();
    }@Override
    public List<Integer> getPid() {
        return academyMapper.getPid();
    }

}
