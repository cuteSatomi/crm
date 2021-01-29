package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Menu;
import com.zzx.crm.mapper.MenuMapper;
import com.zzx.crm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-29 14:56:03
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryForRoot() {
        return menuMapper.queryForRoot();
    }
}
