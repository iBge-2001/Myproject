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
public class Dormitory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sid;

    private Integer buildingNo;

    private Integer dormitory;
    private Integer classid;

}
