package ru.connor.springMVC.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.connor.springMVC.dao.PersonDAO;
import ru.connor.springMVC.model.Person;

@Component
public class PersonValidator implements Validator{


    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Person person = (Person) object;
        if (personDAO._show_(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email already taken");
        }
    }
}