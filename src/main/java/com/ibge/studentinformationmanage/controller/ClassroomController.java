package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.entity.Classroom;
import com.ibge.studentinformationmanage.service.ClassService;
import com.ibge.studentinformationmanage.service.ClassroomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibge
 * @since 2023-06-20
 */
@Slf4j
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    //新增课室
    @PostMapping()
    public Result<String> save(HttpServletRequest request,@RequestBody Classroom classroom){
        log.info("新增课室信息：{}",classroom.toString());
        //默认status：1 空闲
        classroom.setStatus("1");
        classroomService.save(classroom);
        return Result.success("新增课室成功",Code.POST_OK);
    }
    /**
     * 课室信息分页查询
     * @param page
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/page")
    public Result<Page> pageResult(int page, int pageSize){
        log.info("page = {},pageSize = {}, name = {}",page,pageSize);
        //构造页面构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Classroom> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
//        queryWrapper.like(StringUtils.isNotEmpty(classroom),Classroom::get,classroom);
        //添加排序条件
        queryWrapper.orderByDesc(Classroom::getInsertTime);
        //执行查询
        classroomService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo,Code.GET_OK);
    }

    @PutMapping()
    public Result<String> update(HttpServletRequest request, @RequestBody Classroom classroom   ){
        log.info(classroom.toString());
        //获得线程id
        long id = Thread.currentThread().getId();
        log.info("线程id为{}",id);
        LambdaQueryWrapper<Classroom> classroomLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomLambdaQueryWrapper.eq(Classroom::getClassroom,classroom.getClassroom());
        classroomService.update(classroomLambdaQueryWrapper);
        return Result.success("员工信息修改成功",Code.UPDATE_OK);
    }
    @GetMapping("/{object}")
    public Result< List<Classroom>> getByid(@PathVariable Object object ){
        log.info("根据{}查找课室",object);
        LambdaQueryWrapper<Classroom> classroomLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomLambdaQueryWrapper.like(Classroom::getClassroom,object);
        List<Classroom> list = classroomService.list(classroomLambdaQueryWrapper);
        if (list!=null){
            return Result.success(list,Code.GET_OK);
        }
        else {
            return Result.error("查无",Code.GET_ERR);
        }
    }
    @GetMapping("/classroomCount")
    public Result<Integer> getUnuseClassroom(){
        LambdaQueryWrapper<Classroom> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(Classroom::getStatus,1);
        int count = classroomService.count(objectLambdaQueryWrapper);
        return  Result.success(count,Code.GET_OK);
    }
}

