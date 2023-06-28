package com.ibge.studentinformationmanage.entity;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String classroom;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String insertUser;

    private String updateUser;


}
