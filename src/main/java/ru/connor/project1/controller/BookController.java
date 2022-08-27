package ru.connor.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.connor.project1.dao.BooksDAO;
import ru.connor.project1.model.Books;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksDAO booksDAO;

    @Autowired
    public BookController(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("book")Books books, Model model){
        model.addAttribute("books", booksDAO.index());
        return "/project1/book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.show(id));
        return "project1/book/show";
    }

    @GetMapping("/new")
    public String toCreatePage(@ModelAttribute("book") Books book){
        return "project1/book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Books book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "project1/book/new";
        }
        booksDAO.add(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String toEditPage(@ModelAttribute("book") Books book){
        return "project1/book/edit";
    }

    @PatchMapping()
    public String edit(@ModelAttribute("book") @Valid Books book, int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "project1/book/edit";
        }
        booksDAO.update(book, id);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksDAO.delete(id);
        return "redirect:/books";
    }
}
