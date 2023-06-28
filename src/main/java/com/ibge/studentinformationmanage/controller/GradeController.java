package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.GradeDto;
import com.ibge.studentinformationmanage.entity.Course;
import com.ibge.studentinformationmanage.entity.Grade;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.mapper.GradeMapper;
import com.ibge.studentinformationmanage.service.CourseService;
import com.ibge.studentinformationmanage.service.GradeService;
import com.ibge.studentinformationmanage.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Slf4j
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    GradeMapper gradeMapper;
    @GetMapping("/one")
    public Result<Page> getGradeWithTeacher(int page, int pageSize, HttpSession httpSession){
         String sid = (String) httpSession.getAttribute("user");
        Page<GradeDto> gradeWithTeacher = this.getGradeWithTeacher(page, pageSize, sid);
        return Result.success(gradeWithTeacher,Code.GET_OK);
    }
    public Page<GradeDto> getGradeWithTeacher(int page, int pageSize, String sid) {
        Page<Grade> pageInfo = new Page<>(page,pageSize);
        Page<GradeDto> gradeDtoPageInfo = new Page<>();
        LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Grade::getSid,sid);
        //执行分页查询
        gradeService.page(pageInfo,lambdaQueryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,gradeDtoPageInfo);
        List<Grade> records = pageInfo.getRecords();
        List<GradeDto> gradeDtoList = records.stream().map((item) -> {
            GradeDto gradeDto = new GradeDto();
            BeanUtils.copyProperties(item,gradeDto);

            Integer cid = item.getCid();//课程id
            //根据id查询教师和课程名对象
            LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
            courseLambdaQueryWrapper.eq(Course::getCid,cid);
            Course course = courseService.getOne(courseLambdaQueryWrapper);
            if(course != null){
                String courseTeacher = course.getTeacher();
                String cname = course.getCname();
                gradeDto.setTeacher(courseTeacher);
                gradeDto.setCname(cname);
            }
            return gradeDto;
        }).collect(Collectors.toList());
        gradeDtoPageInfo.setRecords(gradeDtoList);
        return gradeDtoPageInfo;
    }
    @GetMapping("/byProfession")
    public Result<IPage> getGradeBySelection(int page, int pageSize, String selection){
        List<String> sid = gradeMapper.getSid(selection);
        IPage<GradeDto> gradeByProfession = gradeService.getGradeByProfession(page, pageSize, sid);
        System.out.println(gradeByProfession.toString());
        return Result.success(gradeByProfession,Code.GET_OK);
    }

    @GetMapping("/count/{st_profession}")
    public Result<List<GradeDto>> getGrade(@PathVariable String st_profession){
        List<GradeDto> grade = gradeService.GetCount(st_profession);
        Integer code = grade != null ? Code.GET_OK:Code.GET_ERR;
        String msg = grade !=null ? "查询成功":"查询失败";
        System.out.println(grade!=null ? "查询成功":"查询失败");
        System.out.println("listgrade:"+grade);
        return  Result.success(grade,code);
    }
    @GetMapping("/percent")
    public Result<String> getPercent(){
        String percent = gradeService.getPercent();
        Integer code = percent != null ? Code.GET_OK:Code.GET_ERR;
        String msg = percent !=null ? "查询成功":"查询失败";
        System.out.println(percent!=null ? "查询成功":"查询失败");
        return  Result.success(percent,code);
    }
    @PostMapping("/fail")
    public Result<List<GradeDto>> getFail(@RequestBody GradeDto grade){
        List<GradeDto> gradeList = gradeService.getFail(grade);
        Integer code = gradeList != null ? Code.GET_OK:Code.GET_ERR;
        String msg = gradeList !=null ? "查询成功":"查询失败";
        System.out.println(msg);
        System.out.println(gradeList);
        System.out.println(gradeList!=null ? "查询成功":"查询失败");
        return  Result.success(gradeList,code);
    }
}

