package com.zzx.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Log {
    private Long id;
    private Employee opUser;
    private Date opTime;
    private String opIp;
    private String function;
    private String params;
}