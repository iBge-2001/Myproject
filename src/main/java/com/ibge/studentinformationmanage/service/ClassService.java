package com.ibge.studentinformationmanage.service;

import com.ibge.studentinformationmanage.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
public interface ClassService extends IService<Class> {
    Integer getCountByClassId(Integer classid);
    public String getClassCount();
    List<String> getClassname();
    public String getAvgCount();
}
