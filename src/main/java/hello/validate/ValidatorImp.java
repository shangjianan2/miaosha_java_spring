package hello.validate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidatorImp implements InitializingBean {

    Validator validator;

    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if(constraintViolationSet.size() > 0){
            result.setError(true);
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
