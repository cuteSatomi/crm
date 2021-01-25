package com.zzx.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee {
    private Long id;
    private String username;
    private String realname;
    private String password;
    private String tel;
    private String email;
    private Department dept;
    private Date inputTime;
    private Boolean state;
    private Boolean admin;
}