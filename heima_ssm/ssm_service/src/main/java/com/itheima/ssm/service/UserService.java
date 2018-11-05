package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 新增用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    UserInfo findById(String id) throws Exception;

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    List<Role> findOtherRoles(String userId);

    /**
     * 为用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}
