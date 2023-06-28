package com.ibge.studentinformationmanage.service.impl;

import com.ibge.studentinformationmanage.entity.User;
import com.ibge.studentinformationmanage.mapper.UserMapper;
import com.ibge.studentinformationmanage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Boolean update1(String user, Integer status) {
        return userMapper.updateStatus(user,status)>0;
    }
}
