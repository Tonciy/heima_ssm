package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @author Zero
 */
public interface ISysLogService {

    /**
     * 保存日志信息
     * @param sysLog
     * @throws Exception
     */
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    List<SysLog> findAll(Integer page, Integer size) throws Exception;

}
