package com.ibge.studentinformationmanage.dto;

import com.ibge.studentinformationmanage.entity.Class;
import lombok.Data;

@Data
public class ClassDto extends Class {
    private Integer count;
}
