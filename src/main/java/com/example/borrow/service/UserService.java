package com.example.borrow.service;

import com.example.borrow.entity.User;
import com.example.borrow.mapper.BorrowRecordMapper;
import com.example.borrow.mapper.UserMapper;
import com.example.borrow.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final BorrowRecordMapper recordMapper;
    public UserService(UserMapper userMapper,BorrowRecordMapper recordMapper){
        this.userMapper = userMapper;
        this.recordMapper= recordMapper;

    }
    public List<User> list(){
        return userMapper.selectAll();

    }

    public User create(User user){
        userMapper.insert(user);
        return userMapper.selectById(user.getId());

    }

    public List<Map<String,Object>> borrows(Long userId) throws NotFoundException {
        if(userMapper.selectById(userId) == null){
            throw new NotFoundException("用户不存在id"+userId);
        }
        return recordMapper.selectByUserId(userId);
    }


}


























