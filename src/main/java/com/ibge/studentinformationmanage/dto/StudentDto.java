package com.ibge.studentinformationmanage.dto;

import com.ibge.studentinformationmanage.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto extends Student {
    private String classname;
    private String academy;
    private String profession;
    private String cteacher;

}
