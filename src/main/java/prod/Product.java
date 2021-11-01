package prod;

import category.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(name = "category_id", length = 50)
    protected Long categoryId;

    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "type",length = 8)
    private String type;

    @Column(name = "description",length = 100)
    private String description;

}
