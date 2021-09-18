package com.gittigidiyor.quxiotic95.creditapp.dto;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements GenericDTO<CustomerDTO> {

    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    @NotNull(message = "Monthly income must be specified!")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double monthlyIncome;

    @NotBlank(message = "Phone number is required!")
    private String phoneNumber;

    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
