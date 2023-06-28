package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.Academy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Mapper
public interface AcademyMapper extends BaseMapper<Academy> {
    @Select("select distinct pid from academy ")
    public List<Integer> getPid();
    //获取学院有哪些
    @Select("select DISTINCT academy from academy")
    public List<String> getAcademy();
}
