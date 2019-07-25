package com.enes.fullstacktodoapp.full_stack_todo_app.validator;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User)object;

        if(user.getPassword().length()<6){
            errors.rejectValue("password","lenght","Password must be at least 6 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "Match","passwords does not match");
        }
    }
}
