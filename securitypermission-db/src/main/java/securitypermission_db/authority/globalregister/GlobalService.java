package securitypermission_db.authority.globalregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import securitypermission_db.authority.repository.RoleJpa;
import securitypermission_db.authority.repository.UserJpa;

/**
 * 资源库注册
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:15
 */
@Component
public class GlobalService {

    //用户
    @Autowired
    public UserJpa userJpa;

    //角色
    @Autowired
    public RoleJpa roleJpa;

}
