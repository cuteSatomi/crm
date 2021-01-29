package com.zzx.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu {
    private Long id;
    private String text;
    private String iconCls;
    private Boolean checked;
    private String state;
    private String attributes;
    private String function;
    private List<Menu> children;
}