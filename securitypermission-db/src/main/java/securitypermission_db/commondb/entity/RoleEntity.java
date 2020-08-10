package securitypermission_db.commondb.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-25 
 */
@Data
@Entity
@Table ( name ="role" )
public class RoleEntity  {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;
 
   	@Column(name = "name" )
	private String name;

	@Column(name = "description" )
	private String description;

 
 
}