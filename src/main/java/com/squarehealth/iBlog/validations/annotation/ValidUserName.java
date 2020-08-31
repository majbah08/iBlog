package com.squarehealth.iBlog.validations.annotation;



import javax.validation.Constraint;
import javax.validation.Payload;

import com.squarehealth.iBlog.validations.validator.ValidUsernameValidator;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ValidUsernameValidator.class)
@Target({ ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserName {
    public String message() default " Username Already Exists !";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
