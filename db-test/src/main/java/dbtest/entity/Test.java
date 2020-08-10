package dbtest.entity;

import com.cloudutils.jpatemplate.dao.domain.BaseDoMain;

import javax.persistence.*;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-08-10 
 */

@Entity
@Table ( name ="test" )
public class Test extends BaseDoMain {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "field1" )
	private String field1;

   	@Column(name = "field2" )
	private String field2;

   	@Column(name = "field3" )
	private String field3;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}
