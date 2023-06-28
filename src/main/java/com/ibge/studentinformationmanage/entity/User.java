package com.ibge.studentinformationmanage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user;

    private String password;

    /**
     * 0：管理员 ，1：老师，2：学生
     */
    private Integer type;

    /**
     * 0：禁用状态，1：启用状态
     */
    private Integer status;

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
