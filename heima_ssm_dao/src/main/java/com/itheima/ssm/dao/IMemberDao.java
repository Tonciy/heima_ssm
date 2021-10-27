package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author Zero
 */
public interface IMemberDao {

    /**
     * 根据id查询成员
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;
}
