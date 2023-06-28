package com.ibge.studentinformationmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibge.studentinformationmanage.dto.DormitoryDto;
import com.ibge.studentinformationmanage.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
public interface DormitoryService extends IService<Dormitory> {
    IPage<DormitoryDto> findByPage(Integer page, Integer pageSize, Long id);
}
