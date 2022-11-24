package com.psbc.cpabp.mapper.user;

import com.psbc.cpabp.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询用户信息
     * @param name
     * @param password
     * @return
     */
    public TbUser queryUserInfo(String name, String password);

    public List<TbUser> queryUserList();

    /**
     * 删除用户信息
     * @param name
     * @param password
     * @return
     */
    public List<TbUser> deleteUserInfo(String name, String password);

    /**
     * 注册用户信息
     * @return
     */
    public int addUserInfo(TbUser user);

    /**
     * 更改用户信息
     * @return
     */
    public int updateUserByName(TbUser user);


}
