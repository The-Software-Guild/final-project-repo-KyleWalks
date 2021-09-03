/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.controller;

import com.sg.bookkeeper.dto.Book;
import com.sg.bookkeeper.service.BookkeeperService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cosmos
 */
@Controller
public class BookkeeperController {
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    private final BookkeeperService service;

    public BookkeeperController(BookkeeperService service) {
        this.service = service;
    }
    
    @GetMapping("")
    public String homeRedirect() {
        return "redirect:/home";
    }
    
    @GetMapping("home")
    public String homeManage() {
        return "home";
    }
    
    @RequestMapping(value="book-search", method={RequestMethod.GET, RequestMethod.POST})
    public String bookSearch(@RequestParam("bookTitle") String bookTitle, Model model) {
        String title = bookTitle;
        System.out.println(title);
        
        model.addAttribute("title", title);
        
        return "book-search";
    }
    
    @GetMapping("book-view")
    public String bookView(Model model) {
        
        Book book = new Book();
        model.addAttribute(book);
        
        return "book-view";
    }
    
    @PostMapping("add-book")
    public String addBook(Book book) {
        
        service.addBook(book);
        
        return "redirect:/book-list";
    }
    
    @GetMapping("book-list")
    public String bookList(Model model) {
        List<Book> books = service.getAllBooks();
        
        model.addAttribute("books",books);
        
        return "book-list";
    }
}
