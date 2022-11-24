package com.psbc.cpabp.entity;

import lombok.Data;

@Data //lombox注解，自动实现set/get/tostring方法
public class TbUser {

  private String id;
  private String name;
  private String sex;
  private String age;
  private String phone;
  private String birthday;
  private String address;
  private String position;
  private String salary;
  private String email;
  private String password;
  private String state;

}
