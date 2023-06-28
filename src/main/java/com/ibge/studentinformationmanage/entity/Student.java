package com.ibge.studentinformationmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "sid")
    private Long sid;

    /**
     * 邮箱，用于获取验证码
     */
    private String smail;

    /**
     * 姓名
     */
    private String sname;

    /**
     * 地址
     */
    private String saddress;

    /**
     * 电话
     */
    private String sphone;

    /**
     * 性别 1：2 ？ 男：女
     */
    private String ssex;

    /**
     * 专业id
     */
    private Integer spid;

    /**
     * 班级id
     */
    private Integer classid;

    /**
     * 年龄
     */
    private Long sage;

    /**
     * 插入时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    @TableField(fill =FieldFill.INSERT)
    private String createUser;

    /**
     * 更新者
     */
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private String updateUser;


}
