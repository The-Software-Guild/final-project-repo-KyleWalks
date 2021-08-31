/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.service;

import com.sg.bookkeeper.dto.Author;
import com.sg.bookkeeper.dto.Book;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cosmos
 */
@Service
public interface BookkeeperService {
    Author addAuthor(Author author);
    
    Book addBook(Book book);
    
    Author getAuthorById(int authorId);
    
    Book getBookById(int bookId);
    
    List<Author> getAllAuthors();
    
    List<Book> getAllBooks();
    
    void updateAuthor(Author author);

    void updateBook(Book book);
    
    boolean deleteAuthorById(int authorId);
    
    boolean deleteBookById(int bookId);
}
