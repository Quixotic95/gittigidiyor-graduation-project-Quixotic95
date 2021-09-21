package com.gittigidiyor.quxiotic95.creditapp.dto;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import com.gittigidiyor.quxiotic95.creditapp.validation.TCKNConstraint;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.FirstCheck;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.SecondCheck;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerDTO implements GenericDTO<CustomerDTO> {

    @NotBlank(message = "First name is required!")
    @Pattern(regexp = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+", message = "First Name is incorrect!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Pattern(regexp = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+", message = "Last Name is incorrect!")
    private String lastName;

    @NotNull(message = "Monthly income must be specified!")
    @Range(min = 0, message = "Monthly income must be minimum 0!")
    private Double monthlyIncome;

    @Pattern(regexp = "^(5)([0-9]{2})\\s?[0-9]{3}(\\s?([0-9]{2})){2}$", message = "Phone Number format is wrong!")
    @NotBlank(message = "Phone number is required!")
    private String phoneNumber;

    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!", groups = FirstCheck.class)
    @TCKNConstraint(groups = SecondCheck.class)
    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
