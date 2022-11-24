package com.psbc.cpabp.service;

import com.psbc.cpabp.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CpabpService {

    public TbUser queryUserInfo(@Param("name") String name, @Param("password") String password);

    public List<TbUser> deleteUserInfo(String name, String password);

    public int addUserInfo(TbUser user);

    public List<TbUser> queryUserList();

    public int updateUserByName(TbUser user);


}
