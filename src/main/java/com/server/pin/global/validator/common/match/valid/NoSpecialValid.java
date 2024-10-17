package com.server.pin.global.validator.common.match.valid;

import com.server.pin.global.validator.common.match.validator.NoSpecialValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoSpecialValidator.class)
public @interface NoSpecialValid {
    String message() default "Required no Special Character";
    Class[] groups() default {};
    Class[] payload() default {};
}