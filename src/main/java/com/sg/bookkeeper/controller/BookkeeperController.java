/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.controller;

import com.sg.bookkeeper.dto.Author;
import com.sg.bookkeeper.dto.Book;
import com.sg.bookkeeper.service.BookkeeperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
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
    
    // BOOKS
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
    
    @RequestMapping(value="book-review", method={RequestMethod.GET, RequestMethod.POST})
    public String bookReview(@RequestParam("bookId") int bookId, Model model) {
        Book book = service.getBookById(bookId);
        
        model.addAttribute(book);
        
        return "book-review";
    }
    
    @PostMapping("submit-review")
    public String submitBookReview(Book book, Model model) {
        service.updateBook(book);
        
        return "redirect:/book-list";
    }
    
    @RequestMapping(value="delete-book", method={RequestMethod.GET, RequestMethod.POST})
    public String deleteBook(@RequestParam("bookId") int bookId, Model model) {        
        service.deleteBookById(bookId);
        
        model.addAttribute("books", service.getAllBooks());
        
        return "book-list";
    }
    
    // AUTHORS
    @GetMapping("author-view")
    public String authorView(Model model) {
        
        Author author = new Author();
        model.addAttribute(author);
        
        return "author-view";
    }
    
    @PostMapping("add-author")
    public String addAuthor(Author author) {
        service.addAuthor(author);
        
        return "redirect:/author-list";
    }
    
    @GetMapping("author-list")
    public String authorList(Model model) {
        List<Author> authors = service.getAllAuthors();
        
        model.addAttribute("authors",authors);
        
        return "author-list";
    }
    
    @RequestMapping(value="view-author", method={RequestMethod.GET, RequestMethod.POST})
    public String viewAuthor(@RequestParam("authorId") int authorId, Model model) {
        Author author = service.getAuthorById(authorId);
        
        model.addAttribute(author);
        
        return "view-author";
    }
    
    @RequestMapping(value="delete-author", method={RequestMethod.GET, RequestMethod.POST})
    public String deleteAuthor(@RequestParam("authorId") int authorId, Model model) {
        service.deleteAuthorById(authorId);
        
        model.addAttribute("authors", service.getAllAuthors());
        
        return "author-list";
    }
}
