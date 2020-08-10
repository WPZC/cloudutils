package securitypermission.security.fegin;

import entity.PageData;
import entity.RoleEntity;
import entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import vo.ResultVO;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:06
 */
@Component
@FeignClient(value = "securitypermissiondb")
public interface UserMangementFegin {

    @PostMapping(value = "/ac/findByUserName")
    UserEntity findByUserName(@RequestParam("username") String username);

    @PostMapping(value = "/uct/addUser")
    ResultVO<String> addUser(@RequestBody UserEntity userEntity, @RequestParam("roleId")Long roleId);

    @RequestMapping(value = "/uct/updateUser")
    ResultVO<String> updateUser(@RequestBody UserEntity userEntity,@RequestParam("roleId")Long roleId);

    /**
     * 获取用户列表(分页)
     * @param currentPage
     * @return
     */
    @PostMapping(value = "/uct/findByPage")
    ResultVO<PageData<UserEntity>> findByPage(@RequestParam("currentPage") Integer currentPage);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/uct/deleteByuId")
    ResultVO<String> deleteByuId(@RequestParam("id") Long id);

    /**
     * 根据用户id查找角色对象
     * @param id
     * @return
     */
    @PostMapping(value = "/uct/oleRole")
    RoleEntity oleRole(@RequestParam("id") Long id);
}
