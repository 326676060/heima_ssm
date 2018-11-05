package com.itheima.ssm.dao;

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
}
