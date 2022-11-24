package com.psbc.cpabp.mapper.order;

import com.psbc.cpabp.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    /**
     * 查询用户信息
     * @param name
     * @param password
     * @return
     */
    public TbUser queryUserInfo(String name, String password);

    public List<TbUser> queryUserList();


}
