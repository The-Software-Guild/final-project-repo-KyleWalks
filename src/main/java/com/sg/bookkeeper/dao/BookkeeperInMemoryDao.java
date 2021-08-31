/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.dao;

import com.sg.bookkeeper.dto.Author;
import com.sg.bookkeeper.dto.Book;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cosmos
 */
@Repository
public class BookkeeperInMemoryDao implements BookkeeperDao {
    private static final List<Author> authors = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();

    @Override
    public Author addAuthor(Author author) {
        int nextId = authors.stream()
                .mapToInt(i -> i.getAuthorId())
                .max()
                .orElse(0) + 1;

        author.setAuthorId(nextId);
        authors.add(author);
		
        return author;
    }

    @Override
    public Book addBook(Book book) {
        int nextId = books.stream()
                .mapToInt(i -> i.getBookId())
                .max()
                .orElse(0) + 1;

        book.setBookId(nextId);
        books.add(book);
		
        return book;
    }

    @Override
    public Author getAuthorById(int authorId) {
        return authors.stream()
                .filter(i -> i.getAuthorId()== authorId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book getBookById(int bookId) {
        return books.stream()
                .filter(i -> i.getBookId()== bookId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public void updateAuthor(Author author) {
        int index = 0;
        while (index < authors.size()
                && authors.get(index).getAuthorId() != author.getAuthorId()) {
            index++;
        }

        if (index < authors.size()) {
            authors.set(index, author);
        }
    }

    @Override
    public void updateBook(Book book) {
        int index = 0;
        while (index < books.size()
                && books.get(index).getBookId() != book.getBookId()) {
            index++;
        }

        if (index < books.size()) {
            books.set(index, book);
        }
    }

    @Override
    public boolean deleteAuthorById(int id) {
        for (Author author : authors) {
            if (author.getAuthorId() == id) {
                return authors.remove(author);
            }
        }
        return false;
    }

    @Override
    public boolean deleteBookById(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return books.remove(book);
            }
        }
        return false;
    }
    
}
