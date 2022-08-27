package ru.connor.project1.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Books {
    int id;
    @NotEmpty(message = "Book name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+", message = "Valid book name is: Book name")
    String bookName;

    @Pattern(regexp = "[A-Z]\\w+", message = "Valid author name is: Name Surname")
    @NotEmpty(message = "Author name should not be empty")
    String author;

    @Pattern(regexp = "\\d{4}", message = "Count number of year should be 4")
    @NotEmpty(message = "Published Year should not be empty")
    int yearOfPublishing;

    public Books(int id, String bookName, String author, int yearOfPublishing) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
}
