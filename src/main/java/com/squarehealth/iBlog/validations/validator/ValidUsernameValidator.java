package com.squarehealth.iBlog.validations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.squarehealth.iBlog.services.AppUserService;
import com.squarehealth.iBlog.validations.annotation.ValidUserName;

public class ValidUsernameValidator implements ConstraintValidator<ValidUserName, String> {
    @Autowired
    private AppUserService userService;

    @Override
    public void initialize(ValidUserName validUsername) {
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(userService.findByUsername(s).isPresent()){
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Username " + s + " Already Exists")
                .addConstraintViolation();
        return false ;}
        return true;
    }
}
