package securitypermission.security.fegin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * fegin注入
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:32
 */
@Component
public class GlobalFegin {

    @Autowired
    public UserMangementFegin userMangementFegin;

    @Autowired
    public RoleMangementFegin roleMangementFegin;

}
