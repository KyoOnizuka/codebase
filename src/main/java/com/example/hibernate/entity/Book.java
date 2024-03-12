package com.example.hibernate.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    String isbn;

    @NotNull
    String title;

    Book() {}

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}
