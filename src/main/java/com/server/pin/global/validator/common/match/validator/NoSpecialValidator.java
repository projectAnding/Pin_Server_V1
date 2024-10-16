package com.server.pin.global.validator.common.match.validator;

import com.server.pin.global.validator.common.RegexUtils;
import com.server.pin.global.validator.common.match.valid.NoSpecialValid;
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