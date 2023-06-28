package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select tgrade from course where teacher = #{user}")
    public Integer getTrgadeByUser(String user);
}
