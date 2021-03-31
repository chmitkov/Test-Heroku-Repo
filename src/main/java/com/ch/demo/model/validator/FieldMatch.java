package com.ch.demo.model.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

  String message() default "The fields must match!";
  String first();
  String second();
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
