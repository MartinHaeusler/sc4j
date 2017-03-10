package org.neatutils.sc4j.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.neatutils.sc4j.api.Comparison;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RequiredIfConditions.class)
public @interface RequiredIf {

	public String field();

	public Comparison comparison();

	public String compareValue() default "";

}
