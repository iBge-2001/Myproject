package com.ibge.studentinformationmanage.dto;

import com.ibge.studentinformationmanage.entity.Grade;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class GradeDto extends Grade {
    private String cname;
    private String teacher;
    private String sname;
    private String classname;
    private String pname;
    private Double avg;
    private Double sum;
    private Integer count;
    private List<HashMap<Object,Object>> cg;
}
