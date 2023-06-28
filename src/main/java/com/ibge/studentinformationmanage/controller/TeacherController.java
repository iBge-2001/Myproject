package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.entity.*;
import com.ibge.studentinformationmanage.service.CourseService;
import com.ibge.studentinformationmanage.service.GradeService;
import com.ibge.studentinformationmanage.service.StudentService;
import com.ibge.studentinformationmanage.service.TeacherService;
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
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/{user}")
    public Result<Integer> getTgradeByUser(@PathVariable String user){
        Integer trgadeByUser = teacherService.getTrgadeByUser(user);
        return Result.success(trgadeByUser,Code.GET_OK);
    }
    @GetMapping("/byCid")
    public Result<Page> getGradeByCid(HttpSession httpSession, int page, int pageSize){
        String user = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Course> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Course::getTeacher,user);
        Course one = courseService.getOne(teacherLambdaQueryWrapper);
        Integer cid = one.getCid();
        Page<Grade> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getCid,cid);
        gradeService.page(pageInfo,gradeLambdaQueryWrapper);
        return Result.success(pageInfo,Code.GET_OK);
    }
    @GetMapping("/grade/{sid}")
    public Result<Grade> getGrade(HttpSession httpSession,@PathVariable String sid){
        String user = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Course> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Course::getTeacher,user);
        Course one = courseService.getOne(teacherLambdaQueryWrapper);
        Integer cid = one.getCid();
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getCid,cid).eq(Grade::getSid,sid);
        Grade one1 = gradeService.getOne(gradeLambdaQueryWrapper);
        Integer code = one1 != null ? Code.GET_OK:Code.GET_ERR;
        String msg = one1 !=null ? "查询成功":"查询失败";
        System.out.println(one1!=null ? "查询成功":"查询失败");
        return  Result.success(one1,code);

    }
    @PutMapping
    public Result<String> update(@RequestBody Grade grade) {
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getCid,grade.getCid()).eq(Grade::getSid,grade.getSid());
        boolean update = gradeService.update(grade, gradeLambdaQueryWrapper);
        return  Result.success("修改成功",update? Code.UPDATE_OK:Code.UPDATE_ERR);
    }
    @PostMapping
    public Result<String> insGrade(HttpSession httpSession,@RequestBody  Grade grade) {
        String user = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Course> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Course::getTeacher,user);
        Course one = courseService.getOne(teacherLambdaQueryWrapper);
        Integer cid = one.getCid();
        grade.setCid(cid);
        boolean flag =  gradeService.save(grade);
        System.out.println(flag);
        return  Result.success("添加成功",flag? Code.SAVE_OK:Code.Save_ERR);
    }
    @DeleteMapping("/{sid}")
    public Result<String> delete(HttpSession httpSession,@PathVariable long sid) {
        String user = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Course> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Course::getTeacher,user);
        Course one = courseService.getOne(teacherLambdaQueryWrapper);
        Integer cid = one.getCid();
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getCid,cid).eq(Grade::getSid,sid);
        boolean flag  = gradeService.remove(gradeLambdaQueryWrapper);
        return  Result.success("删除成功",flag? Code.DELETE_OK:Code.DELETE_ERR);
    }
    @GetMapping("/getStudentByCid")
    public Result<List<Long>> getStudentByCid(HttpSession httpSession){
        String user = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Course> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Course::getTeacher,user);
        Course one = courseService.getOne(teacherLambdaQueryWrapper);
        Integer pid = one.getPid();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getSpid,pid);
        List<Student> list = studentService.list(studentLambdaQueryWrapper);
        List<Long> collect = list.stream().map(Student::getSid).collect(Collectors.toList());
        return Result.success(collect,Code.GET_OK);
    }
}

