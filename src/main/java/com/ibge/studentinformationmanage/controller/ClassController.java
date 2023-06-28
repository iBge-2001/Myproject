package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.ClassDto;
import com.ibge.studentinformationmanage.entity.Class;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    //添加班级
    @PostMapping("")
    public Result<String> add(@RequestBody Class Class){
        System.out.println(Class.toString());
         classService.save(Class);
        return  Result.success("添加成功",Code.SAVE_OK);
    }
    @PutMapping()
    public Result<String> update(@RequestBody Class Class){
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classLambdaQueryWrapper.eq(com.ibge.studentinformationmanage.entity.Class::getClassid,Class.getClassid());
        classService.update(Class,classLambdaQueryWrapper);
        return Result.success("修改成功",Code.UPDATE_OK);
    }
    @GetMapping("/getAll")
    public Result<Page> getAll(int page,int pageSize){
        Page<Class> pageInfo = new Page<>(page, pageSize);
        Page<ClassDto> classDtoPage = new Page<>();
        List<Class> records = classService.page(pageInfo).getRecords();
        BeanUtils.copyProperties(pageInfo,classDtoPage,"records");
        List<ClassDto> classDtos = records.stream().map((item)->{
            ClassDto classDto = new ClassDto();
            BeanUtils.copyProperties(item,classDto);
            classDto.setCount(classService.getCountByClassId(item.getClassid()));
            return classDto;
        }).collect(Collectors.toList());
        classDtoPage.setRecords(classDtos);
        return Result.success(classDtoPage,Code.GET_OK);
    }
    @DeleteMapping("/{classid}")
    public Result<String> delete(@PathVariable Integer classid){
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classLambdaQueryWrapper.eq(Class::getClassid,classid);
        classService.remove(classLambdaQueryWrapper);
        return  Result.success("删除成功",Code.DELETE_OK);
    }
    @GetMapping("/{classid}")
    public Result<Class> getOne(@PathVariable Integer classid) {
        LambdaQueryWrapper<Class> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Class::getClassid,classid);
        Class one = classService.getOne(lambdaQueryWrapper);
        Integer code = one!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = one!=null ? "查询成功" :"查询失败";
        System.out.println(msg);
        return  Result.success(one,code);
    }

    @GetMapping("/classname")
    public Result<List<String>> getClassname(){
        List<String> list = classService.getClassname();
        System.out.println("pid列表获取成功");
        return Result.success(list,Code.GET_OK);
    }
    @GetMapping("/classcount")
    public Result getClassCount() {
        String Count = classService.getClassCount();
        Integer code = Count !=null ? Code.GET_OK:Code.GET_ERR;
        String msg = Count!=null ? "查询成功" :"查询失败";
        System.out.println(Count!=null ? "查询成功" :"查询失败");
        return  Result.success(Count,code);
    }
    @GetMapping("/avgcount")
    public Result<String> getAvgCount() {
        String Count = classService.getAvgCount();
        Integer code = Count !=null ? Code.GET_OK:Code.GET_ERR;
        String msg = Count!=null ? "查询成功" :"查询失败";
        System.out.println(Count!=null ? "查询成功" :"查询失败");
        return  Result.success(Count,code);
    }
}

