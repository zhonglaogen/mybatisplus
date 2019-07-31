package com.zlx.pls.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlx.pls.entity.User;

/**
 * mybatis plus 只需写mapper,不需要写方法,继承BaseMapper
 */
public interface UserMapper extends BaseMapper<User> {

}
