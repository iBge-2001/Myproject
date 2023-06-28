package com.ibge.studentinformationmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Academy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */

    private Integer pid;

    /**
     * 学院
     */
    private String academy;
    /*
    * 学院代码
    * */
    private Integer aid;
    /**
     * 辅导员
     */
    private String instructor;
    private String pname;

}
