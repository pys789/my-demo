package cn.pys.utils;


import cn.pys.exception.ResException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Description
 * @Date 2020/11/12 15:19
 * @Created by pengys
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void validateEntity(Object object, Class<?>... groups){
        Set<ConstraintViolation<Object>> validates = validator.validate(object, groups);
        if(!validates.isEmpty()){
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> validate:validates){
                msg.append(validate.getMessage()).append("<br>");
            }
            throw new ResException(msg.toString());
        }

    }
}
