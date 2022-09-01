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
        return PersonDAO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDAO._findEmail_(person.getEmail()).isPresent()){
            errors.rejectValue("email", "", "This Email already taken");
        }
    }
}