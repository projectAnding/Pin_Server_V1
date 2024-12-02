package com.server.pin.global.common.validator.match.validator;

import com.server.pin.global.common.validator.RegexUtils;
import com.server.pin.global.common.validator.match.valid.NoSpecialValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpecialValidator implements ConstraintValidator<NoSpecialValid, String> {

    @Override
    public void initialize(NoSpecialValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !RegexUtils.hasSpecialChar(value);
    }

}