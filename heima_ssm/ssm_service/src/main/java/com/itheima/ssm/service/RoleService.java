package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 新增保存角色
     *
     * @param role
     */
    void save(Role role);

    /**
     * 根据角色id查询角色详情
     *
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 根据roleId查询未添加的角色
     *
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId);

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
