package domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class PageQueryCriteria
{


    //@NotBlank
    private String query;

    @NotBlank
    String orderBy = "id";

    @NotBlank
    String sortOrder = "ASC";

    @PositiveOrZero
    Integer currentPage = 0;

    @Min(1)
    @Positive
    Integer pageSize = 10;
}
