package securitypermission_db.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import securitypermission_db.commondb.entity.UserEntity;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 10:56
 */
public interface UserJpa extends JpaRepository<UserEntity, Integer> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Query(value = "select user from UserEntity user where user.id=?1")
    UserEntity findByUserId(Long id);

    /**
     * 修改用户
     * @param id
     * @param username
     * @param password
     * @param iphone
     * @param sex
     * @param organizationName
     * @param organizationNum
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity user set user.username = ?2 , user.password=?3 ," +
            " user.iphone = ?4 , user.sex = ?5 ,user.organizationName = ?6 , user.organizationName = ?7 where user.id = ?1")
    int updateUser(Long id, String username, String password,
                          String iphone, Integer sex, String organizationName, String organizationNum);

    /**
     * 根据用户Id删除对象
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from UserEntity where id=?1")
    void deleteByuId(Long id);

}
