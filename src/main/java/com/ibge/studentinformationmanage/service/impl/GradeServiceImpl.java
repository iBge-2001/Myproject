package com.ibge.studentinformationmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.controller.Result;
import com.ibge.studentinformationmanage.dto.GradeDto;
import com.ibge.studentinformationmanage.entity.Course;
import com.ibge.studentinformationmanage.entity.Grade;
import com.ibge.studentinformationmanage.mapper.CourseMapper;
import com.ibge.studentinformationmanage.mapper.GradeMapper;
import com.ibge.studentinformationmanage.service.CourseService;
import com.ibge.studentinformationmanage.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public IPage<GradeDto> getGradeByProfession(Integer page, Integer pageSize, List<String> sid) {

        Page<GradeDto> gradeDtoPage = new Page<>(page, pageSize);
        IPage<GradeDto> gradeByProfession = gradeMapper.getGradeByProfession(gradeDtoPage, sid);
        List<GradeDto> list=gradeByProfession.getRecords().stream().map((item)->{
            long sid1 = item.getSid();
            List<HashMap<Object,Object>> cnameByPro = courseMapper.getCnameByPro(sid1);
            GradeDto avgandSum = gradeMapper.getAvgandSum(sid1);
            System.out.println(avgandSum.toString());
            Double avg = avgandSum.getAvg();
            System.out.println(avg);
            item.setAvg(avg);
            Double sum = avgandSum.getSum();
            System.out.println(sum);
            item.setSum(sum);
            item.setCg(cnameByPro);
            return item;
    }).collect(Collectors.toList());
    gradeByProfession.setRecords(list);
        return gradeByProfession;
    }
    @Override
    public List<GradeDto> getFail(Grade grade) {
        return gradeMapper.getFail(grade);
    }
    @Override
    public String getPercent() {
        return gradeMapper.getPercent();
    }
    @Override
    public List<GradeDto> GetCount(String st_profession) {
        return gradeMapper.GetCount(st_profession);
    }

}
