package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 返回新增用户的用户名
     *
     * @return
     */
    @RequestMapping("getUserName")
    @ResponseBody
    public String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        /* User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/
        return username;
    }

    /**
     * 查询所有用户
     *
     * @param model
     * @return
     */
    @RequestMapping("findAll")
    public String findAll(Model model) {
        List<UserInfo> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user-list";
    }

    /**
     * 新增保存用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("save")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll";
    }

    /**
     * 根据id查询用户详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("findById")
    public String findById(String id, Model model) throws Exception {
        UserInfo userInfo = userService.findById(id);
        model.addAttribute("user", userInfo);
        return "user-show";
    }

    /**
     * 查询用户以及用户可以添加的角色
     *
     * @param id
     * @return
     */
    @RequestMapping("findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id, Model model) throws Exception {
        UserInfo userInfo = userService.findById(id);
        List<Role> roleList = userService.findOtherRoles(id);
        model.addAttribute("user", userInfo);
        model.addAttribute("roleList", roleList);
        return "user-role-add";
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roleIds
     * @param model
     * @return
     */
    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam("userId") String userId, @RequestParam("ids") String[] roleIds, Model model) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }
}
