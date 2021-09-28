package com.gittigidiyor.quixotic95.loanappthymeleaf.model;

import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.TCKNConstraint;
import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group.FirstCheck;
import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group.SecondCheck;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
public class Customer {

    private UUID id;

    @NotBlank(message = "First name is required!", groups = FirstCheck.class)
    @Pattern(regexp = "^[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?([(\\s)$]?+[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?)+", message = "First Name is incorrect!", groups = SecondCheck.class)
    private String firstName;

    @NotBlank(message = "Last name is required!", groups = FirstCheck.class)
    @Pattern(regexp = "^[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?([(\\s)$]?+[a-zA-ZÜİÖÇĞŞıüöçğş]+[.]?)+", message = "Last Name is incorrect!", groups = SecondCheck.class)
    private String lastName;

    @NotNull(message = "Monthly income must be specified!", groups = FirstCheck.class)
    @Range(min = 0, message = "Monthly income must be minimum 0!", groups = SecondCheck.class)
    private Double monthlyIncome;

    @Pattern(regexp = "^05[0-9]{9}$", message = "Phone Number format is wrong!", groups = SecondCheck.class)
    @NotBlank(message = "Phone number is required!", groups = FirstCheck.class)
    private String phoneNumber;

    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!", groups = FirstCheck.class)
    @TCKNConstraint(groups = SecondCheck.class)
    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
