package com.gittigidiyor.quixotic95.loanappthymeleaf.model;

import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.TCKNConstraint;
import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group.FirstCheck;
import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group.SecondCheck;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TcknModel {

    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!", groups = FirstCheck.class)
    @TCKNConstraint(groups = SecondCheck.class)
    @NotBlank(message = "TCKN is mandatory!")
    private String tckn;

}
