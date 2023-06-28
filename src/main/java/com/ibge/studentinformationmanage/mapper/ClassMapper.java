package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.Class;
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
public interface ClassMapper extends BaseMapper<Class> {

    @Select("SELECT COUNT(student.sid) as count FROM student where classid = #{classid}")
    public Integer getCountByClassId(Integer classid);

    @Select("select classname from class ")
    public List<String> getClassname();
    @Select("SELECT COUNT(class.classname) FROM class")
    public String getClassCount();
    @Select("SELECT ROUND(AVG(count.count),0) As count FROM count")
    public String getAvgCount();
}
