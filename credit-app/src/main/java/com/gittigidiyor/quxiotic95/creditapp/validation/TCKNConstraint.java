package com.gittigidiyor.quxiotic95.creditapp.validation;

import com.gittigidiyor.quxiotic95.creditapp.validation.validator.TCKNConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TCKNConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TCKNConstraint {

    String message() default "TCKN is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
