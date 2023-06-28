package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.entity.Course;
import com.ibge.studentinformationmanage.entity.Teacher;
import com.ibge.studentinformationmanage.service.CourseService;
import com.ibge.studentinformationmanage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    //新增课室1
    @PostMapping()
    public Result<String> save(HttpServletRequest request,@RequestBody Course course){
        log.info("新增课程信息：{}",course.toString());
        courseService.save(course);
        return Result.success("新增课程成功",Code.SAVE_OK);
    }
    /**
     * 课程信息分页查询
     * @param page
     * @param pageSize
     * @param object
     * @return
     */
    @GetMapping("/page")
    public Result<Page> pageResult(int page, int pageSize,String object){
        log.info("page = {},pageSize = {}, object = {}",page,pageSize,object);
        //构造页面构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(object),Course::getCname,object).or().like(StringUtils.isNotEmpty(object),Course::getTeacher,object)
                .or().like(StringUtils.isNotEmpty(object),Course::getCid,object);
        //添加排序条件
        queryWrapper.orderByDesc(Course::getTgrade);
        //执行查询
        courseService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo,Code.GET_OK);
    }

    @PutMapping()
    public Result<String> update(HttpServletRequest request, @RequestBody Course course){
        log.info(course.toString());
        //获得线程id
        long id = Thread.currentThread().getId();
        log.info("线程id为{}",id);
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseLambdaQueryWrapper.eq(Course::getTid,course.getTid());
        courseService.update(course,courseLambdaQueryWrapper);
        return Result.success("成功",Code.UPDATE_OK);
    }
    @PutMapping("/tgrade")
    public Result<String> updateTgrade(HttpServletRequest request, @RequestBody Course course){
        log.info(course.toString());
        Boolean aBoolean = courseService.updateTgrade(course);
         String msg = aBoolean != false ? "评分成功":"评分失败";
        Integer code = aBoolean != false ? Code.UPDATE_OK:Code.UPDATE_ERR;
        return Result.success(msg,code);
    }
    @GetMapping("/{cid}")
    public Result<Course> getByid(@PathVariable long cid ){
        log.info("根据cid{}查找课程",cid);
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getCid,cid);
        Course one = courseService.getOne(lambdaQueryWrapper);
        String teacher = one.getTeacher();
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacher,teacher);
        Integer tid = teacherService.getOne(teacherLambdaQueryWrapper).getTid();
        Course courseDto = new Course();
        BeanUtils.copyProperties(one,courseDto);
        courseDto.setTid(tid);
        if (one!=null){
            return Result.success(courseDto,Code.GET_OK);
        }
        else {
            return Result.error("查无",Code.GET_ERR);
        }
    }
}


