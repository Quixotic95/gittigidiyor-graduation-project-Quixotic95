package com.gittigidiyor.quxiotic95.creditapp.dto;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import com.gittigidiyor.quxiotic95.creditapp.validation.TCKNConstraint;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.FirstCheck;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.SecondCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerDTO implements GenericDTO<CustomerDTO> {

    @ApiModelProperty(example = "A. Emre", value = "First name of the customer.")
    @NotBlank(message = "First name is required!")
    @Pattern(regexp = "^[\\p{L}]+[.]?([(\\s)$]?+[\\p{L}]+[.]?)+", message = "First Name is incorrect!")
    private String firstName;

    @ApiModelProperty(example = "Oğuz", value = "Last name of the customer.")
    @NotBlank(message = "Last name is required!")
    @Pattern(regexp = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+", message = "Last Name is incorrect!")
    private String lastName;

    @ApiModelProperty(example = "7000", value = "Monthly income of the customer.")
    @NotNull(message = "Monthly income must be specified!")
    @Range(min = 0, message = "Monthly income must be minimum 0!")
    private Double monthlyIncome;

    @ApiModelProperty(example = "5413711566", value = "Phone number of the customer without 0 at the beginning and without any spacing.")
    @Pattern(regexp = "^(5)[0-9]{9}$", message = "Phone Number format is wrong!")
    @NotBlank(message = "Phone number is required!")
    private String phoneNumber;

    @ApiModelProperty(example = "23570297588", value = "TCKN of the customer.")
    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!", groups = FirstCheck.class)
    @TCKNConstraint(groups = SecondCheck.class)
    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
