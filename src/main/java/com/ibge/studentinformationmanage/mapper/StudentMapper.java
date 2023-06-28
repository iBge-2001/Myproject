package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
