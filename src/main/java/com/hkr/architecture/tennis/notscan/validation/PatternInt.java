package com.hkr.architecture.tennis.notscan.validation;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PatternInt.PatternIntCheck.class})
public @interface PatternInt {

    String message() default "验证失败";

    int[] val() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class PatternIntCheck implements ConstraintValidator<PatternInt, Integer> {

        @Override
        public void initialize(PatternInt constraintAnnotation) {
            System.out.println(constraintAnnotation);

        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            PatternInt patternInt = (PatternInt) ((ConstraintDescriptorImpl) ((ConstraintValidatorContextImpl) context).getConstraintDescriptor()).getAnnotation();

            for (int i = 0; i < patternInt.val().length; i++) {
                if (value == patternInt.val()[i]) {
                    return true;
                }
            }
            return false;
        }
    }
}
