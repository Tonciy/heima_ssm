package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zero
 */
@Repository
public interface ISysLogDao {

    /**
     * 保存日志信息
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into sysLog(visitTime, username, ip, url, executionTime, method) values(#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method}) ")
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    @Select("select * from sysLog ")
    List<SysLog> findAll() throws Exception;
}
