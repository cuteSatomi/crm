package com.zzx.crm.service;

import com.zzx.crm.domain.Log;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-27 16:48:37
 */
public interface LogService {
    int deleteByPrimaryKey(Long id);
    int insert(Log record);
    Log selectByPrimaryKey(Long id);
    List<Log> selectAll();
    int updateByPrimaryKey(Log record);
}
