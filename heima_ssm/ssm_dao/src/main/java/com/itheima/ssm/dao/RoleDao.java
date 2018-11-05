package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    /**
     * 根据用户id查询角色（多对多）
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findPermissionByRoleId")),
    })
    List<Role> findByUserId(String userId);

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 新增保存角色
     *
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据角色id查询角色详情
     *
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "users", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.UserDao.findByRoleId")),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findPermissionByRoleId")),
    })
    Role findById(String id);

    /**
     * 根据roleId查询permission
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    /**
     * 为角色添加权限
     *
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
