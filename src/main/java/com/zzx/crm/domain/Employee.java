package com.zzx.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;
    private Boolean state;
    private Boolean admin;
    private List<Role> roles = new ArrayList<Role>();
}