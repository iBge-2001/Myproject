package com.ibge.studentinformationmanage.dto;

import com.ibge.studentinformationmanage.entity.Dormitory;
import lombok.Data;

@Data
public class DormitoryDto extends Dormitory {
    private String sname;
    private String classname;
    private String academy;
}
