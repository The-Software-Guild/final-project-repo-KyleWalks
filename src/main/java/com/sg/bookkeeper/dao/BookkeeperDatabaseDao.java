/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.dao;

import com.sg.bookkeeper.dto.Author;
import com.sg.bookkeeper.dto.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cosmos
 */
@Repository
public class BookkeeperDatabaseDao implements BookkeeperDao {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BookkeeperDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Author addAuthor(Author author) {
        final String sql = "INSERT INTO author(authorName,birthDate,authorLink,bio,alternateNames) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, author.getAuthorName());
            statement.setString(2, author.getBirthdate());
            statement.setString(3, author.getAuthorLink());
            statement.setString(4, author.getBio());
            statement.setString(5, author.getAlternateNames());
            return statement;

        }, keyHolder);

        author.setAuthorId(keyHolder.getKey().intValue());

        return author;
    }

    @Override
    public Book addBook(Book book) {
        final String sql = "INSERT INTO book(description,title,subjects,subjectPlaces,subjectPeople,subjectTimes,bookAuthorName,bookReview) VALUES(?,?,?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, book.getDescription());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getSubjects());
            statement.setString(4, book.getSubjectPlaces());
            statement.setString(5, book.getSubjectPeople());
            statement.setString(6, book.getSubjectTimes());
            statement.setString(7, book.getBookAuthorName());
            statement.setString(8, book.getBookReview());
            return statement;

        }, keyHolder);

        book.setBookId(keyHolder.getKey().intValue());
                
        return book;
    }

    @Override
    public Author getAuthorById(int authorId) {
        final String sql = "SELECT * FROM author WHERE authorId = ?;";
        
        return jdbcTemplate.queryForObject(sql, new authorMapper(), authorId);
    }

    @Override
    public Book getBookById(int bookId) {
        final String sql = "SELECT * FROM book WHERE bookId = ?;";
        
        return jdbcTemplate.queryForObject(sql, new bookMapper(), bookId);
    }
    
    @Override
    public List<Author> getAllAuthors() {
        final String sql = "SELECT * FROM author;";
        
        return jdbcTemplate.query(sql, new authorMapper());
    }

    @Override
    public List<Book> getAllBooks() {
        final String sql = "SELECT * FROM book;";
        
        return jdbcTemplate.query(sql, new bookMapper());
    }
    
    @Override
    public void updateAuthor(Author author) {
        final String sql = "UPDATE author "
                + "SET authorName = ?, birthdate = ?, authorLink = ?, "
                + "bio = ?, alternateNames "
                + "WHERE authorId = ?;";

        jdbcTemplate.update(sql, author.getAuthorName(), Date.valueOf(author.getBirthdate()), 
                author.getAuthorLink(),author.getBio(), 
                author.getAlternateNames(), author.getAuthorId());
    }

    @Override
    public void updateBook(Book book) {
        final String sql = "UPDATE book "
                + "SET description = ?, title = ?, subjects = ?, "
                + "subjectPeople = ?, subjectPlaces = ?, subjectTimes = ?, "
                + "bookAuthorName = ?, bookReview = ? "
                + "WHERE bookId = ?;";

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, book.getDescription());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getSubjects());
            statement.setString(4, book.getSubjectPeople());
            statement.setString(5, book.getSubjectPlaces());
            statement.setString(6, book.getSubjectTimes());
            statement.setString(7, book.getBookAuthorName());
            statement.setString(8, book.getBookReview());
            statement.setInt(9, book.getBookId());
            return statement;
        });
    }

    @Override
    public boolean deleteAuthorById(int authorId) {
        final String sql = "DELETE FROM author "
                + "WHERE authorId = ?;";

        return jdbcTemplate.update(sql, authorId) == 1;
    }

    @Override
    public boolean deleteBookById(int bookId) {
        final String sql = "DELETE FROM book "
                + "WHERE bookId = ?;";

        return jdbcTemplate.update(sql, bookId) == 1;
    }
    
    private static final class authorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int index) throws SQLException {
            Author author = new Author();
            
            author.setAuthorId(rs.getInt("authorId"));
            author.setAuthorName(rs.getString("authorName"));
            author.setBirthdate(rs.getString("birthdate"));
            author.setAuthorLink(rs.getString("authorLink"));
            author.setBio(rs.getString("bio"));
            author.setAlternateNames(rs.getString("alternateNames"));
            
            return author;
        }
    }
    
    private static final class bookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int index) throws SQLException {
            Book book = new Book();
            
            book.setBookId(rs.getInt("bookId"));
            book.setDescription(rs.getString("description"));
            book.setTitle(rs.getString("title"));
            book.setSubjects(rs.getString("subjects"));
            book.setSubjectPlaces(rs.getString("subjectPlaces"));
            book.setSubjectPeople(rs.getString("subjectPeople"));
            book.setSubjectTimes(rs.getString("subjectTimes"));
            book.setBookAuthorName(rs.getString("bookAuthorName"));
            book.setBookReview(rs.getString("bookReview"));
            
            return book;
        }
    }
}
