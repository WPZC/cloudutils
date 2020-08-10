package securitypermission.security.base.controller;

import entity.PageData;
import entity.RoleEntity;
import methods.Backtrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import securitypermission.security.base.service.RoleService;
import securitypermission.security.jwt.JwtUtil;
import vo.ResultVO;
import vo.view.RoleMenuView;
import vo.view.TreeEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 15:12
 */
@Controller
@RequestMapping(value = "/rct")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @RequestMapping(value = "/addRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO addRole(String name, String description){

        if(null==roleService.addRole(name,description)){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(true,"操作成功");
    }

    /**
     * 删除角色
     * @param rId
     * @return
     */
    @RequestMapping(value = "/deleteByRId",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO deleteByRId(Long rId){

        roleService.deleteByRId(rId);

        return Backtrack.success(true,"操作成功");

    }

    /**
     * 编辑角色
     * @param roleEntity
     * @return
     */
    @RequestMapping(value = "/updateRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO updateRole(RoleEntity roleEntity){

        roleService.updateRole(roleEntity);

        return Backtrack.success(true,"操作成功");

    }

    /**
     * 获取角色列表
     * @param currentPage 页码‘
     * @return
     */
    @RequestMapping(value = "/getRoleMsgs",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<RoleEntity>> getRoleMsgs(Integer currentPage){

        PageData<RoleEntity> list = roleService.getRoleMsgs(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 获取角色列表LIST
     * @return
     */
    @RequestMapping(value = "/getRoleMsgsList",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<List<RoleEntity>> getRoleMsgsList(){

        List<RoleEntity> list = roleService.getRoleMsgsList();

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 获取tree对象
     * @return
     */
    @RequestMapping(value = "/getNodes",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<TreeEntity> getNodes(Long rId){

        TreeEntity list = roleService.getNodes(rId);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 保存角色菜单
     * @return
     */
    @RequestMapping(value = "/saveNodes",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<String> saveNodes(Long rId, String nodes){

        String rs = roleService.saveNodes(rId, Arrays.stream(nodes.split(",")).collect(Collectors.toList()));

        if(null==rs){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(null,"操作成功");

    }


    /**
     * 获取该角色绑定的菜单
     * @return
     */
    @PostMapping(value = "/getRoleMenuMsg")
    @ResponseBody
    public ResultVO<List<RoleMenuView>> getRoleMenuMsg(HttpServletRequest request){

        List<RoleMenuView> roleEntities = roleService.getRoleMenuMsg(JwtUtil.getUser(request));

        if(null==roleEntities){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(roleEntities);

    }





}
