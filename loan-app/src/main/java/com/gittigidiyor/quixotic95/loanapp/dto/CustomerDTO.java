package com.gittigidiyor.quixotic95.loanapp.dto;

import com.gittigidiyor.quixotic95.loanapp.validation.TCKNConstraint;
import com.gittigidiyor.quixotic95.loanapp.validation.group.FirstCheck;
import com.gittigidiyor.quixotic95.loanapp.validation.group.SecondCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CustomerDTO {

    @ApiModelProperty(hidden = true)
    private UUID id;

    @ApiModelProperty(example = "A. Emre", value = "First name of the customer.")
    @NotBlank(message = "First name is required!", groups = FirstCheck.class)
    @Pattern(regexp = "^[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?([(\\s)$]?+[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?)+", message = "First Name is incorrect!", groups = SecondCheck.class)
    private String firstName;

    @ApiModelProperty(example = "Oğuz", value = "Last name of the customer.")
    @NotBlank(message = "Last name is required!", groups = FirstCheck.class)
    @Pattern(regexp = "^[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?([(\\s)$]?+[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?)+", message = "Last Name is incorrect!", groups = SecondCheck.class)
    private String lastName;

    @ApiModelProperty(example = "7000", value = "Monthly income of the customer.")
    @NotNull(message = "Monthly income must be specified!", groups = FirstCheck.class)
    @Range(min = 0, message = "Monthly income must be minimum 0!", groups = SecondCheck.class)
    private Double monthlyIncome;

    @ApiModelProperty(example = "05413711566", value = "Phone number of the customer must without any spacing.")
    @Pattern(regexp = "^05[0-9]{9}$", message = "Phone Number format is wrong!", groups = SecondCheck.class)
    @NotBlank(message = "Phone number is required!", groups = FirstCheck.class)
    private String phoneNumber;

    @ApiModelProperty(example = "23570297588", value = "TCKN of the customer.")
    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!", groups = FirstCheck.class)
    @TCKNConstraint(groups = SecondCheck.class)
    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
