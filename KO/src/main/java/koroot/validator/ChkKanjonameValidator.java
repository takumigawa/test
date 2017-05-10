package koroot.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.teeda.extension.annotation.validator.Validator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD})
@Validator("TChkKanjonameValidator")
public @interface ChkKanjonameValidator {

	String target() default "";
	
	String targetId();

}
