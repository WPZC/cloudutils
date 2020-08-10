package securitypermission.security.base.service.impl;

import entity.PageData;
import entity.RoleEntity;
import entity.UserEntity;
import methods.Backtrack;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import securitypermission.security.base.service.UserService;
import securitypermission.security.fegin.UserMangementFegin;

import java.util.Date;

/**
 * 用户实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMangementFegin userMangementFegin;
    @Override
    public String addUser(UserEntity userEntity, Long roleId) {
        return Backtrack.checkMsg(userMangementFegin.addUser(userEntity, roleId));
    }

    @Override
    public String updateUser(UserEntity userEntity,Long roleId) {
        return Backtrack.checkMsg(userMangementFegin.updateUser(userEntity, roleId));
    }

    @Override
    public PageData<UserEntity> findByPage(Integer currentPage) {
        PageData<UserEntity> list = Backtrack.checkData(userMangementFegin.findByPage(currentPage));
        return list;
    }

    @Override
    public String deleteByuId(Long id) {
        return Backtrack.checkMsg(userMangementFegin.deleteByuId(id));
    }

    @Override
    public RoleEntity oleRole(Long id) {
        return userMangementFegin.oleRole(id);
    }


}
