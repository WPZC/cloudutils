package securitypermission_db.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import securitypermission_db.commondb.entity.RoleMenu;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 16:51
 */
public interface RoleMenuJpa extends JpaRepository<RoleMenu,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM role_menu WHERE r_id=?1")
    List<RoleMenu> findByRId(Long rId);

}
