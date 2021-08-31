/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.service;

import com.sg.bookkeeper.dao.BookkeeperDao;
import com.sg.bookkeeper.dto.Author;
import com.sg.bookkeeper.dto.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Cosmos
 */
public class BookkeeperServiceLayer implements BookkeeperService {
    
    private final BookkeeperDao dao;

    @Autowired
    public BookkeeperServiceLayer(BookkeeperDao dao) {
        this.dao = dao;
    }
    
    @Override
    public Author addAuthor(Author author) {
        return dao.addAuthor(author);
    }

    @Override
    public Book addBook(Book book) {
        return dao.addBook(book);
    }

    @Override
    public Author getAuthorById(int authorId) {
        if (dao.getAllAuthors().size() < 1 || authorId == 0) {
            return null;
        }
        Author author = dao.getAuthorById(authorId);
        
        return author;
    }

    @Override
    public Book getBookById(int bookId) {
        if (dao.getAllBooks().size() < 1 || bookId == 0) {
            return null;
        }
        Book book = dao.getBookById(bookId);
        
        return book;
    }

    @Override
    public List<Author> getAllAuthors() {
        return dao.getAllAuthors();
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    public void updateAuthor(Author author) {
        if (author == null)
            return;
        
        dao.updateAuthor(author);
    }

    @Override
    public void updateBook(Book book) {
        if (book == null)
            return;
        
        dao.updateBook(book);
    }

    @Override
    public boolean deleteAuthorById(int authorId) {
        List<Author> authorList = dao.getAllAuthors();
        Author author = getAuthorById(authorId);
        if (authorList.size() < 1 || authorId == 0 || author == null) {
            return false;
        }
        
        return dao.deleteAuthorById(authorId);
    }

    @Override
    public boolean deleteBookById(int bookId) {
        List<Book> bookList = dao.getAllBooks();
        Book book = getBookById(bookId);
        if (bookList.size() < 1 || bookId == 0 || book == null) {
            return false;
        }
        
        return dao.deleteBookById(bookId);
    }
    
}
