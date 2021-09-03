/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bookkeeper.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Cosmos
 */
public class Author {
    private int authorId;
    private String authorName;
    private LocalDate birthdate;
    private String authorLink;
    private String bio;
    private String alternateNames;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(String alternateNames) {
        this.alternateNames = alternateNames;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.authorId;
        hash = 97 * hash + Objects.hashCode(this.birthdate);
        hash = 97 * hash + Objects.hashCode(this.authorLink);
        hash = 97 * hash + Objects.hashCode(this.bio);
        hash = 97 * hash + Objects.hashCode(this.alternateNames);
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
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorLink, other.authorLink)) {
            return false;
        }
        if (!Objects.equals(this.bio, other.bio)) {
            return false;
        }
        if (!Objects.equals(this.alternateNames, other.alternateNames)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        return true;
    }
    
    
}
