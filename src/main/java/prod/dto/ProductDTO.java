package prod.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private String location;
    private BigDecimal price;
    private Integer quantityAvailable;
    private String productCode;
    private String imageOrVideo;
    private String brand;
    private String lookAfterMe;
    private String aboutMe;
}
