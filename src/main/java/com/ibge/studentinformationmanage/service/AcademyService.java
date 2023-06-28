package com.ibge.studentinformationmanage.service;

import com.ibge.studentinformationmanage.entity.Academy;
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
public interface AcademyService extends IService<Academy> {
    List<Integer> getPid();
    List<String> getAcademy();

}
