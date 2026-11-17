package com.example.borrow.mapper;

import com.example.borrow.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper。
 */
public interface UserMapper {

    /** 查所有用户。 */
    List<User> selectAll();

    /** 按 id 查用户，查不到返回 null。 */
    User selectById(@Param("id") Long id);

    /** 新增用户，主键回填。 */
    int insert(User user);
}

