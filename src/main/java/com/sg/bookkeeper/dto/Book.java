/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.dto;

import java.util.Objects;

/**
 *
 * @author Cosmos
 */
public class Book {
    private int bookId;
    private String description;
    private String title;
    private String cover;
    private String subjects;
    private String subjectPlaces;
    private String subjectPeople;
    private String subjectTimes;
    private String bookAuthorName;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getSubjectPlaces() {
        return subjectPlaces;
    }

    public void setSubjectPlaces(String subjectPlaces) {
        this.subjectPlaces = subjectPlaces;
    }

    public String getSubjectPeople() {
        return subjectPeople;
    }

    public void setSubjectPeople(String subjectPeople) {
        this.subjectPeople = subjectPeople;
    }

    public String getSubjectTimes() {
        return subjectTimes;
    }

    public void setSubjectTimes(String subjectTimes) {
        this.subjectTimes = subjectTimes;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.bookId;
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.title);
        hash = 73 * hash + Objects.hashCode(this.cover);
        hash = 73 * hash + Objects.hashCode(this.subjects);
        hash = 73 * hash + Objects.hashCode(this.subjectPlaces);
        hash = 73 * hash + Objects.hashCode(this.subjectPeople);
        hash = 73 * hash + Objects.hashCode(this.subjectTimes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.bookId != other.bookId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.cover, other.cover)) {
            return false;
        }
        if (!Objects.equals(this.subjects, other.subjects)) {
            return false;
        }
        if (!Objects.equals(this.subjectPlaces, other.subjectPlaces)) {
            return false;
        }
        if (!Objects.equals(this.subjectPeople, other.subjectPeople)) {
            return false;
        }
        if (!Objects.equals(this.subjectTimes, other.subjectTimes)) {
            return false;
        }
        return true;
    }
    
    
}
