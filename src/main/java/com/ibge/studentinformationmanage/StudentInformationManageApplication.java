package com.ibge.studentinformationmanage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 学生功能：
 * 1、学生个人信息，查看，修改
 * 2、查询成绩
 * 3、查询专业成绩排名
 * 4、查询空教室
 * 5、教师评价
 * 6、查找宿舍信息
 * 7、查看学院信息
 * 8、修改个人密码，需要邮箱验证
 * 9、注册，只有学生需要注册，教师通过管理员添加
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
public class StudentInformationManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentInformationManageApplication.class, args);
        log.info("项目启动成功");
    }

}
