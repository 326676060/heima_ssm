package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService  {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 新增保存角色
     * @param role
     */
    void save(Role role);

    /**
     * 根据角色id查询角色详情
     * @param id
     * @return
     */
    Role findById(String id);
}
