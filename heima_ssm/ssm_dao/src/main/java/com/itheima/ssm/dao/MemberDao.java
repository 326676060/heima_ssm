package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberDao {
    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId) throws Exception;
}
