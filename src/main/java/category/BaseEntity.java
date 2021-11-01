package category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity implements Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
    @Column(name = "id",  nullable = false)
    protected Long id;


    @JsonIgnore
    public List<String> getDefaultSearchFields(){
        return new ArrayList<String>();
    }
}
