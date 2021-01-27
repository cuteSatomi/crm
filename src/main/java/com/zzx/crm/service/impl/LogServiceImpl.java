package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Log;
import com.zzx.crm.mapper.LogMapper;
import com.zzx.crm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-27 16:48:47
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Log record) {
        return logMapper.insert(record);
    }

    @Override
    public Log selectByPrimaryKey(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return logMapper.updateByPrimaryKey(record);
    }
}
