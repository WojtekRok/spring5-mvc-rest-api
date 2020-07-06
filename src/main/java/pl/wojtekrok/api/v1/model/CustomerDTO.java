package pl.wojtekrok.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @ApiModelProperty(value = "required", required = true)
    private String firstName;

    @ApiModelProperty(value = "required", required = true)
    private String lastName;

    @ApiModelProperty(value = "assigned automatically")
    private String customerURL;
}
