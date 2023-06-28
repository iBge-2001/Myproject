package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.entity.*;
import com.ibge.studentinformationmanage.service.AcademyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;
    @GetMapping("/profession")
    public Result<List<Academy>> getProfession() {
        List<Academy> profession = academyService.list();
        Integer code = profession !=null ? Code.GET_OK:Code.GET_ERR;
        System.out.println(profession!=null ? "查询成功" :"查询失败");
        return  Result.success(profession,code);
    }
    @GetMapping()
    public Result<List<String>> getAcademy() {
        List<String> academy = academyService.getAcademy();
        System.out.println(academy);
        Integer code = academy !=null ? Code.GET_OK:Code.GET_ERR;
        String msg = academy!=null ? "查询成功" :"查询失败";
        System.out.println(msg);
        System.out.println(academy!=null ? "查询成功" :"查询失败");
        return  Result.success(academy,code);
    }
    @GetMapping("/detail")
    public Result<Page> getByProId(int page, int pageSize, String selection) {
        log.info("academy:{}",selection);
        Page<Academy> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Academy> academyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        academyLambdaQueryWrapper.eq(Academy::getAcademy,selection);
        Page<Academy> academyPage = academyService.page(pageInfo, academyLambdaQueryWrapper);
        Integer code = academyPage!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = academyPage!=null ? "查询成功" :"查询失败";
        System.out.println(msg);
        return  Result.success(academyPage,code);
    }
    @GetMapping("/{aid}")
    public Result<Academy> getByid(@PathVariable long aid ){
        log.info("根据aid{}查找课程",aid);
        LambdaQueryWrapper<Academy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Academy::getAid,aid);
        Academy one = academyService.getOne(lambdaQueryWrapper);
        if (one!=null){
            return Result.success(one,Code.GET_OK);
        }
        else {
            return Result.error("查无",Code.GET_ERR);
        }
    }
    @PutMapping()
    public Result<String> updateMe(@RequestBody Academy academy){
        LambdaQueryWrapper<Academy> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Academy::getAid,academy.getAid());
        boolean update = academyService.update(academy, studentLambdaQueryWrapper);
        if (update == true){
            return Result.success("修改成功",Code.UPDATE_OK);
        }else return Result.error("修改失败",Code.UPDATE_ERR);
    }
    @DeleteMapping("/{aid}")
    public Result<String> delete(@PathVariable Integer aid) {
        LambdaQueryWrapper<Academy> academyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        academyLambdaQueryWrapper.eq(Academy::getAid,aid);
        boolean flag  = academyService.remove(academyLambdaQueryWrapper);
        System.out.println(flag);
        return  Result.success("删除成功",flag? Code.DELETE_OK:Code.DELETE_ERR);
    }
    @PostMapping()
    public Result<String> save(@RequestBody Academy academy){
        log.info("新增课程信息：{}",academy.toString());
        academyService.save(academy);
        return Result.success("新增学院成功",Code.SAVE_OK);
    }
    @GetMapping("/pid")
    public Result<List<Integer>> getPid(){
        List<Integer> list = academyService.getPid();
        System.out.println("pid列表获取成功");
        return Result.success(list,Code.GET_OK);
    }

}

