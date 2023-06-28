package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.dto.StudentDto;
import com.ibge.studentinformationmanage.entity.*;
import com.ibge.studentinformationmanage.entity.Class;
import com.ibge.studentinformationmanage.service.AcademyService;
import com.ibge.studentinformationmanage.service.ClassService;
import com.ibge.studentinformationmanage.service.StudentService;
import com.ibge.studentinformationmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

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
@RequestMapping("/student")
public class StudentController {
        @Autowired
        private StudentService studentService;
        @Autowired
        private ClassService classService;
        @Autowired
        private AcademyService academyService;
        @Autowired
        private UserService userService;
    @GetMapping("/getMe")
    public Result<StudentDto> getByid(HttpSession httpSession){
        String Sid = (String) httpSession.getAttribute("user");
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getSid,Sid);
        Student one = studentService.getOne(lambdaQueryWrapper);
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(one,studentDto);
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classLambdaQueryWrapper.eq(Class::getClassid,studentDto.getClassid());
        String classname = classService.getOne(classLambdaQueryWrapper).getClassname();
        String cteacher = classService.getOne(classLambdaQueryWrapper).getCteacher();
        if(classname != null && cteacher != null){
        studentDto.setClassname(classname);
        studentDto.setCteacher(cteacher);
        }
        LambdaQueryWrapper<Academy> academyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        academyLambdaQueryWrapper.eq(Academy::getPid,studentDto.getSpid());
        String acacedmy = academyService.getOne(academyLambdaQueryWrapper).getAcademy();
        String pname = academyService.getOne(academyLambdaQueryWrapper).getPname();
        if (acacedmy != null && pname!= null){
            studentDto.setAcademy(acacedmy);
            studentDto.setProfession(pname);
        }
        System.out.println(studentDto);
            return Result.success(studentDto,Code.GET_OK);
    }
    @GetMapping("/getAll")
    public Result<Page> getAll(int page,int pageSize,HttpSession httpSession,String classname){
        Integer classid=null;
        Student one ;
        String sid = (String) httpSession.getAttribute("user");
        //根据学生id查找classid
        LambdaQueryWrapper<Student> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(classname ==null,Student::getSid,sid);
        if (classname ==null){
             one = studentService.getOne(objectLambdaQueryWrapper);
             classid = one.getClassid();
        }
        //根据班级名称查找classid
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classLambdaQueryWrapper.eq(classname != null,Class::getClassname,classname);
        if (classname !=null){
            classid = classService.getOne(classLambdaQueryWrapper).getClassid();
        }
        //构造分页构造器对象
        Page<Student> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
//        queryWrapper.eq(classname == null,Student::getClassid,classid);
        queryWrapper.eq(Student::getClassid,classid);
        //添加排序条件
        queryWrapper.orderByDesc(Student::getSid);
        //执行分页查询
        studentService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo,Code.GET_OK);
    }
    @PutMapping()
    public Result<String> updateMe(@RequestBody Student student){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getSid,student.getSid());
        boolean update = studentService.update(student, studentLambdaQueryWrapper);
        if (update == true){
            return Result.success("修改成功",Code.UPDATE_OK);
        }else return Result.error("修改失败",Code.UPDATE_ERR);
    }
    @GetMapping("/{sid}")
    public Result<Student> getStudentBySid(@PathVariable long sid){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getSid,sid);
        Student one = studentService.getOne(studentLambdaQueryWrapper);
        return Result.success(one,Code.GET_OK);
    }
    @PutMapping("/update")
    public Result<String> update(@RequestBody Student student){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getSid,student.getSid());
        boolean update = studentService.update(student, studentLambdaQueryWrapper);
        if (update == true){
            return Result.success("修改成功",Code.UPDATE_OK);
        }else return Result.error("修改失败",Code.UPDATE_ERR);
    }
    @PostMapping()
    public Result<String> save( @RequestBody Student student){
        log.info("新增学生信息：{}",student.toString());
        studentService.save(student);
        Long sid = student.getSid();
        User user = new User();
        user.setUser(sid.toString());
        user.setType(2);
        try {
            user.setPassword(DigestUtils.md5DigestAsHex(sid.toString().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userService.save(user);
        return Result.success("新增学生成功",Code.SAVE_OK);
    }
    @DeleteMapping("/{sid}")
    public Result<String> delete(@PathVariable String sid) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getSid,sid);
        boolean flag  = studentService.remove(studentLambdaQueryWrapper);
        System.out.println(flag);
        return  Result.success("删除成功",flag? Code.DELETE_OK:Code.DELETE_ERR);
    }
    @GetMapping("/count")
    public Result getCount(){
        Integer count = studentService.count();
        Integer code = count != null ? Code.GET_OK:Code.GET_ERR;
        String msg = count !=null ?"查询成功" :"查询失败";
        System.out.println(count !=null ? "查询成功" :"查询失败");
        return  Result.success(count,code);
    }
}

