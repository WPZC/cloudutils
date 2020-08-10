package securitypermission.security.service;

import entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import securitypermission.security.entity.AuthUser;
import securitypermission.security.fegin.GlobalFegin;

/**
 * @Author WQY
 * @Date 2019/11/26 14:55
 * @Version 1.0
 */
@Service
@Primary
public class CustomUserDetailsServiceImpl extends GlobalFegin implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMangementFegin.findByUserName(username);
 
        if (user == null) {
            throw new UsernameNotFoundException("user: " + username + " is not found.");
        }
 
        return new AuthUser(user.getUsername(), user.getPassword(), roleMangementFegin.findByUserRole(user.getId()));
    }
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
 
}
 