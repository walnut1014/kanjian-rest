package name.walnut.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	

	private Long id;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public void setId(Long id) {		
		this.id = id;
	}

}
