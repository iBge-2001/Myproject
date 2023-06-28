package com.ibge.studentinformationmanage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibge.studentinformationmanage.dto.DormitoryDto;
import com.ibge.studentinformationmanage.dto.GradeDto;
import com.ibge.studentinformationmanage.entity.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface GradeMapper extends BaseMapper<Grade> {

    @Select("select distinct sid from coursewithprofession where pname = #{selection}")
    public List<String> getSid(String selection);
//    @Select("select * from coursewithprofession where sid = #{sid} ")
    @Select({ "<script>"+
            "select distinct sid,sname,classname,pname from coursewithprofession where  sid  in "+
            "<foreach item = 'item' index = 'index' collection = 'sid' open='(' separator=',' close=')'>"+
            "#{item}" +
            "</foreach>" +
            "</script>"}
    )
    IPage<GradeDto> getGradeByProfession(@Param("page") IPage<GradeDto> page, List<String> sid);
    @Select("SELECT\n" +
            "\tgrade.sid, \n" +
            "\tAVG(grade.grade) as avg, \n" +
            "\tSUM(grade.grade) as sum\n" +
            "FROM\n" +
            "\tgrade\n" +
            "WHERE\n" +
            "\tgrade.sid = #{sid}")
    public GradeDto getAvgandSum(Long sid);
    @Select("select sid,grade,pname,sname from gradelist where cname = '${cname}' and pname = '${pname}' and grade < 60")
    public List<GradeDto> getFail(Grade grade);

    @Select("SELECT CONCAT(ROUND((SELECT COUNT(grade.sid) FROM grade WHERE grade.grade < 60)/ (SELECT COUNT(grade.sid) FROM grade)*100,2),'%') As 百分比;")
    public String getPercent();
    @Select("SELECT COUNT( gradelist.sid ) as count,gradelist.pname,gradelist.cname \n" +
            "FROM gradelist WHERE gradelist.grade < 60  AND gradelist.pname = #{st_profession} GROUP BY gradelist.cname ORDER BY gradelist.cname ASC")
    public List<GradeDto> GetCount(String st_profession);

}
