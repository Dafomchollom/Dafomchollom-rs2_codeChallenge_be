package prod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class AddProductDTO {

    private String location;
    private BigDecimal price;
    private Integer quantityAvailable;
    private String productCode;
    private String imageOrVideo;
    private String brand;
    private String lookAfterMe;
    private String aboutMe;
    private String subCategory2Id;
}
