package securitypermission.security.controller;

import entity.RoleEntity;
import entity.UserEntity;
import methods.Backtrack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import securitypermission.security.entity.AuthUser;
import securitypermission.security.fegin.GlobalFegin;
import securitypermission.security.jwt.JwtUtil;
import vo.ResultVO;

import java.util.List;

/**
 * @Author WQY
 * @Date 2019/11/26 15:42
 * @Version 1.0
 */
@Controller
public class UserController extends GlobalFegin {

    @RequestMapping(value = "/token",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultVO login(@RequestParam String username, @RequestParam String password) {

        System.out.println("获取token");

        if(null==username||username.equals("")){
            return Backtrack.errot("用户名不能为空");
        }

        UserEntity user = userMangementFegin.findByUserName(username);

        if (user == null || !user.getPassword().equals(password)) {
            return Backtrack.errot("用户名或密码错误");
        }

        //Jwts.
        //ResultVO<Object> success = new ResultVO<>();
        //用户名密码正确，生成token给客户端
        //success.setCode(0);
        List<RoleEntity> roles = roleMangementFegin.findByUserRole(user.getId());
        //success.setData(JwtUtil.generateToken(username, roles));

        return Backtrack.success(JwtUtil.generateToken(username, roles),null);

    }

    /**
     * 认证
     * @param token
     * @return
     */
    @RequestMapping(value = "/verificationToken",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultVO<Boolean> verificationToken(@RequestParam("token") String token){

        AuthUser authUser = JwtUtil.parseToken(token);

        authUser.getUsername();

        return Backtrack.success(true);

    }
}