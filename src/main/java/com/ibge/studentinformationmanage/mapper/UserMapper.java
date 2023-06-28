package com.ibge.studentinformationmanage.mapper;

import com.ibge.studentinformationmanage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibge
 * @since 2023-06-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE user set status = #{status} where user = #{user}")
    public int updateStatus(String user,Integer status);
}
