package com.ibge.studentinformationmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibge.studentinformationmanage.entity.Student;
import com.ibge.studentinformationmanage.entity.User;
import com.ibge.studentinformationmanage.service.StudentService;
import com.ibge.studentinformationmanage.service.UserService;
import com.ibge.studentinformationmanage.utils.BaseContext;
import com.ibge.studentinformationmanage.utils.CodeUtils;
import com.ibge.studentinformationmanage.utils.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    SMSUtils smsUtils;

    @PostMapping("/sendMsg/{email}")
    public Result<String> sendMsg(@PathVariable String email, HttpSession httpSession) {
        log.info("email{}",email);
        if (StringUtils.isNotEmpty(email)) {
            //生成随机的4位验证码
            String code = CodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //调用qq邮箱发送验证码
            //smsUtils.sendSimpleMail(email, code);
            //需要将生成的验证码保存到Session
            httpSession.setAttribute("code", code);
            return Result.success("发送成功", Code.SEND_OK);
        } else return Result.error("发送失败", Code.SEND_ERR);
    }
    @PutMapping("/create")
    public Result<String> createUser(@RequestBody Map map,HttpSession httpSession){
        log.info(map.toString());
        //获取学号，也是账号
        String sid = map.get("sid").toString();
        BaseContext.setCurrentId(sid);
        System.out.println(BaseContext.getCurrentId());
        //获取密码
        String password = map.get("password").toString();
        System.out.println(password);
        //获取邮箱
        String mail = map.get("email").toString();
        //获取code
        String code = map.get("code").toString();
        //获取session中保存的验证码
        Object codeInSession = httpSession.getAttribute("code");
        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if(codeInSession != null && codeInSession.equals(code)){
            //如果能够比对成功
            Student student = new Student();
            student.setSid(Long.valueOf(sid));
            student.setSmail(mail);
            studentService.save(student);
            User user = new User();
            user.setUser(sid);
            try {
                user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            user.setType(2);
            userService.save(user);
            return Result.success("注册成功",Code.SAVE_OK);
        }
        else return Result.error("注册失败",Code.Save_ERR);
    }
    @PutMapping("/updatePassword")
    public Result<String> updatePassword(@RequestBody Map map,HttpSession httpSession){
        log.info(map.toString());
        User user = new User();
        user.setStatus(1);
        //获取账号类型，如果是学生则要判断邮箱
        String type =  map.get("type").toString();
        String username = (String) httpSession.getAttribute("user");

        //获取密码
        String password = map.get("password").toString();
        password =  DigestUtils.md5DigestAsHex(password.getBytes());
        String repassword = map.get("repassword").toString();
        repassword =  DigestUtils.md5DigestAsHex(repassword.getBytes());
        //获取当前密码
        user.setUser(username);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUser,user.getUser());
        User one = userService.getOne(userLambdaQueryWrapper);
        String nowpassword = one.getPassword();
        //当前密码验证成功
        if(repassword.equals(nowpassword)){
                user.setPassword(password);
        }
        //学生需要验证邮箱
        if(type.equals(2)){
            //获取邮箱
            String mail = map.get("email").toString();
            //获取code
            String code = map.get("code").toString();
            Object codeInSession = httpSession.getAttribute("code");
            LambdaQueryWrapper<Student> s = new LambdaQueryWrapper<>();
            s.eq(Student::getSid,username);
            String smail = studentService.getOne(s).getSmail();
            if (smail.equals(mail) && codeInSession != null && codeInSession.equals(code)){
                userService.update(user,userLambdaQueryWrapper);
            }
        }else
        {
           userService.update(user,userLambdaQueryWrapper);
        }
        return Result.success("修改成功",Code.UPDATE_OK);
    }
    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody User user){
        log.info(user.toString());
        //获取密码
        String password = user.getPassword();
        System.out.println(password);
        try {
            password = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("修改密码加密：{}",password);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUser,user.getUser());
        user.setPassword(password);
            userService.update(user,userLambdaQueryWrapper);
            return Result.success("修改成功",Code.UPDATE_OK);
    }
    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user){
        //获取页面提交过来的密码
        String password = user.getPassword();
        System.out.println("页面提交过来的密码"+password);
        //调工具类md5加密
        try {
            password = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("登录密码加密：{}",password);
        //根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //eq(实体类：：查询字段，条件值)相当于where
        queryWrapper.eq(User::getUser,user.getUser());
        User one = userService.getOne(queryWrapper);
        //判断是否为空
        if (one ==null){
            return Result.error("登录失败",Code.POST_ERR);
        }
        //比较密码是否一致
        if (!one.getPassword().equals(password)) {
            return Result.error("登陆失败",Code.POST_ERR);
        }
        //密码正确查看user的status是0还是1
        if(one.getStatus()==0){
            //控制台打印
            System.out.println("账户呗禁用");
            return Result.error("账号被禁用",Code.BUSINESS_ERR);
        }
        else{
            request.getSession().setAttribute("user",one.getUser());
            return Result.success(one,Code.POST_OK);
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("logout");
        // 清除Session中的用户信息
        session.removeAttribute("user");
        // 返回登录页面
        return "redirect:/login.html";
    }
    @GetMapping
    public Result<User> home(@SessionAttribute(required = false) User user) {
        System.out.println(user);
        if (user != null) {
            return  Result.success(user,Code.GET_OK);
        } else {
            return  Result.error("未登录",Code.GET_ERR);
        }
    }
    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list,Code.GET_OK);
    }
    @GetMapping("/one/{user}")
    public Result<User> getUserByUser(@PathVariable String user){
        log.info("user{}",user);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUser,user);
        User one = userService.getOne(userLambdaQueryWrapper);
        return Result.success(one,Code.GET_OK);
    }
    @PostMapping("/new")
    public Result<String> InsertNewUser(@RequestBody User user){
        log.info(user.toString());
        //获取密码
        String password = user.getPassword();
        System.out.println(password);
        try {
            password = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setPassword(password);
        userService.save(user);
        return Result.success("添加成功",Code.SAVE_OK);
    }
    @DeleteMapping("/{user}")
    public Result<String> delete(@PathVariable String user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUser,user);
        boolean flag  = userService.remove(userLambdaQueryWrapper);
        System.out.println(flag);
        return  Result.success("删除成功",flag? Code.DELETE_OK:Code.DELETE_ERR);
    }
    @PutMapping("/status")
    public Result<String> updateStatus(@RequestBody User user){
        String user1 = user.getUser();
        Integer status = user.getStatus();
        log.info(user1,status);
        userService.update1(user1,status);
        return Result.success("修改状态成功",Code.UPDATE_OK);
    }
}
