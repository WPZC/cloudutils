package securitypermission.security.base.controller;

import entity.PageData;
import entity.RoleEntity;
import entity.UserEntity;
import methods.Backtrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import securitypermission.security.base.service.UserService;
import securitypermission.security.jwt.JwtUtil;
import vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户
 * @author
 * @version 1.0
 * @date 2020/6/11 16:35
 */
@Controller
@RequestMapping(value = "/uct")
public class UserBaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO addUser(UserEntity userEntity, Long roleId){

        try {
            userEntity.setSavetime(new Date());
            String rs = userService.addUser(userEntity,roleId);

            if(rs.equals("success")){
                return Backtrack.success(userEntity,"操作成功");
            }

           return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof RuntimeException ){
                return Backtrack.errot(e.getMessage());
            }
            return Backtrack.errot("操作失败");
        }


    }


    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO updateUser(UserEntity userEntity,Long roleId){

       try {
           String rs =  userService.updateUser(userEntity,roleId);
            if("success".equals(rs)){
                return Backtrack.success(userEntity,"操作成功");
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
           if(e instanceof RuntimeException ){
               return Backtrack.errot(e.getMessage());
           }
            return Backtrack.errot("操作失败");
        }
    }

    /**
     * 获取用户列表
     * @param currentPage 页码‘
     * @return
     */
    @RequestMapping(value = "/findByPage",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<UserEntity>> list(Integer currentPage){

        PageData<UserEntity> list = userService.findByPage(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO deleteById(Long id){

        userService.deleteByuId(id);

        return Backtrack.success(true,"操作成功");

    }

    @RequestMapping(value = "/oleRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO oleRole(Long id){

        try {
            RoleEntity roleEntity =  userService.oleRole(id);
            if(roleEntity!=null){
                return Backtrack.success(roleEntity,"操作成功");
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            return Backtrack.errot("操作失败");
        }
    }


    @RequestMapping(value = "/gerUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<UserEntity> gerUser(HttpServletRequest request){

        try {

            UserEntity user = JwtUtil.getUser(request);
            user.setPassword(null);
            return Backtrack.success(user);
        }catch (Exception e){
            return Backtrack.errot("操作失败");
        }
    }
}
