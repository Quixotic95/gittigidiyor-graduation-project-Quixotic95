package com.gittigidiyor.quixotic95.loanappthymeleaf.validation;

import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.validator.TCKNConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("unused")
@Constraint(validatedBy = TCKNConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TCKNConstraint {

    String message() default "TCKN is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
