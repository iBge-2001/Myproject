package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.DormitoryDto;
import com.ibge.studentinformationmanage.entity.Class;
import com.ibge.studentinformationmanage.entity.Dormitory;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.service.ClassService;
import com.ibge.studentinformationmanage.service.DormitoryService;
import com.ibge.studentinformationmanage.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @GetMapping("/GetOne")
    public Result<IPage> getOne(int page, int pageSize, HttpSession httpSession) {
        String sid = (String) httpSession.getAttribute("user");
        Page<Object> pageInfo = new Page<>(page,pageSize);
        IPage<DormitoryDto> dormitoryServiceByPage = dormitoryService.findByPage(page, pageSize, Long.valueOf(sid));
        return Result.success(dormitoryServiceByPage,Code.GET_OK);
    }
    @GetMapping()
    public Result<Page> getAll(int page, int pageSize) {
        Page<Dormitory> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dormitory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Dormitory::getSid);
        dormitoryService.page(pageInfo,lambdaQueryWrapper);
        return Result.success(pageInfo,Code.GET_OK);
    }
}

