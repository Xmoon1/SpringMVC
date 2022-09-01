package ru.connor.springMVC.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.connor.springMVC.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    private final SessionFactory sessionFactory;

    public PersonDAO(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> _index_(){
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//        Session session = ss
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    public Optional<Person> _findEmail_(String email){
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();

    }

    @Transactional(readOnly = true)
    public Person _show_(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);

        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void _save_(Person person){
//       jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?, ?, ?)",
//               person.getName(), person.getAge(), person.getEmail());
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void _update_(int id, Person updatedPerson){
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
//                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());

    }

    @Transactional
    public void _delete_(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
        Session session = sessionFactory.getCurrentSession();

        session.delete(session.get(Person.class, id));
    }
}