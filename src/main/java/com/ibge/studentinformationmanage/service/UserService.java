package com.ibge.studentinformationmanage.service;

import com.ibge.studentinformationmanage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
public interface UserService extends IService<User> {
    public Boolean update1(String user,Integer status);
}
