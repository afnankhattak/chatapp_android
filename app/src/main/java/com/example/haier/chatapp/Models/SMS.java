package com.example.haier.chatapp.Models;

import java.util.Date;

/**
 * Created by haier on 11/22/2018.
 */




public class SMS {

    private String id;
    private String text;
    private Author author;
    private Date createdAt;

    public SMS(){

    }

    public SMS(String id, String text, Author author, Date createdAt) {

        //todo: set message id
        this.id =id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }


    public String getText() {
        return text;
    }


    public Author getUser() {
        return author;
    }


    public Date getCreatedAt() {
        return createdAt;
    }
}
