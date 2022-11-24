package com.psbc.cpabp.service.impl;

import com.psbc.cpabp.entity.TbUser;
import com.psbc.cpabp.mapper.order.OrderMapper;
import com.psbc.cpabp.mapper.user.UserMapper;
import com.psbc.cpabp.service.CpabpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CpabpServiceImpl implements CpabpService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public TbUser queryUserInfo(String name, String password) {

        log.info("queryUserInfo...");
        return orderMapper.queryUserInfo(name, password);
    }

    @Override
    public List<TbUser> queryUserList() {

        log.info("queryUserList...");
        return userMapper.queryUserList();
    }

    @Override
    public List<TbUser> deleteUserInfo(String name, String password) {
        return null;
    }

    @Override
    public int addUserInfo(TbUser user) {
        log.info("addUserInfo...");
        return userMapper.addUserInfo(user);
    }

    @Override
    public int updateUserByName(TbUser user) {
        log.info("updateUserByName...");
        return userMapper.updateUserByName(user);
    }

}
