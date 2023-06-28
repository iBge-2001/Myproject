package com.ibge.studentinformationmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.DormitoryDto;
import com.ibge.studentinformationmanage.dto.GradeDto;
import com.ibge.studentinformationmanage.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
public interface GradeService extends IService<Grade> {
    IPage<GradeDto> getGradeByProfession(Integer page, Integer pageSize, List<String> sid);
    public List<GradeDto> getFail(Grade grade);
    public String getPercent();
    public List<GradeDto> GetCount(String st_profession);
}
