package securitypermission.security.config;

import methods.utils.SpringUtils;
import securitypermission.security.fegin.UserMangementFegin;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 14:25
 */
public class BeanFactory {

    private static UserMangementFegin userMangementFegin = SpringUtils.getApplicationContext().getBean(UserMangementFegin.class);

    public static UserMangementFegin getUserMangementFegin(){
        return userMangementFegin;
    }
}
