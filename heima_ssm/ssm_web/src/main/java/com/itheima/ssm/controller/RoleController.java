package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 根据roleId查询角色，和未添加的权限
     *
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(@RequestParam("id") String roleId, Model model) {
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);
        return "role-permission-add";
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     * @param model
     * @return
     */
    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") String roleId,@RequestParam("ids") String[] permissionIds,Model model){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }

    /**
     * 查询所有角色
     *
     * @param model
     * @return
     */
    @RequestMapping("findAll")
    public String findAll(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roleList", roles);
        return "role-list";
    }

    /**
     * 新增添加角色
     *
     * @param role
     */
    @RequestMapping("save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll";
    }

    /**
     * 根据角色id查询角色详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("findById")
    public String findById(String id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role-show";
    }

}
