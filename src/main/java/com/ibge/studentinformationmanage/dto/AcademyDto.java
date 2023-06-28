package com.ibge.studentinformationmanage.dto;

import com.ibge.studentinformationmanage.entity.Academy;
import com.ibge.studentinformationmanage.entity.Class;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AcademyDto extends Academy {


    private Map<Integer,String> classname;

}
