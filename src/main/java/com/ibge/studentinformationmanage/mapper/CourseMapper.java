package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
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
public interface CourseMapper extends BaseMapper<Course> {
//    @Select("select cname,grade from coursewithprofession where sid = #{sid}")
    @Select(
            "select grade,cname from coursewithprofession where sid  = #{sid} "
           )
    public List<HashMap<Object,Object>> getCnameByPro(Long sid);
    @Update("UPDATE course set tgrade = (tgrade + #{tgrade})/2 where cid = #{cid}")
    public int updateTgrade(Course course);
}
