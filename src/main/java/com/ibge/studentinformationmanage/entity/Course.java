package com.ibge.studentinformationmanage.entity;

import com.baomidou.mybatisplus.annotation.Version;
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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    private Integer cid;

    /**
     * 课程名
     */
    private String cname;

    /**
     * 专业id
     */
    private Integer pid;

    /**
     * 教师
     */
    private String teacher;

    /**
     * 教师评分
     */
    private Integer tgrade;

    /**
     * 教师id
     */
    private Integer tid;

}
