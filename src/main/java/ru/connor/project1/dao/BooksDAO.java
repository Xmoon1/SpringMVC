package ru.connor.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.connor.project1.model.Books;

import java.util.List;

@Component
public class BooksDAO {

    private final JdbcTemplate jdbcTemplate;


    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Books> index(){
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Books.class));
    }

    public Books show(int id){
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Books.class))
                .stream().findAny().orElse(null);
    }

    public void add(Books book){
        jdbcTemplate.update("INSERT INTO Book(name, author, publishedyear) VALUES (?, ?, ?)",
                book.getBookName(), book.getAuthor(), book.getYearOfPublishing());
    }

    public void update(Books book, int id){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, publishedyear=? WHERE id=?",
                book.getBookName(), book.getAuthor(), book.getYearOfPublishing(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
