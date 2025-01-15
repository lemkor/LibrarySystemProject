package com.lemkor.springboot.LibraryProjectBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "forename can not be empty")
    @Size(min = 2, max = 30, message = "forename should be between 2 and 30 characters")
    @Column(name = "forename")
    private String forename;

    @NotEmpty(message = "surname can not be empty")
    @Size(min = 2, max = 30, message = "surname should be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @Min(value = 0, message = "age can not be negative")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Person(){

    }

    public Person(String forename, String surname, int age) {
        this.forename = forename;
        this.surname = surname;
        this.age = age;
    }

    public Person(int id, String forename, String surname, int age) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        if(this.books == null){
            this.books = new ArrayList<>();
            this.books.addAll(books);
        }else {
            this.books = books;
        }
    }

    public void addBook(Book book){
        if(this.books == null){
            this.books = new ArrayList<>();
            this.books.add(book);
        }else{
            this.books.add(book);
        }
    }

    public void deleteBook(Book book){
        this.books.remove(book);
    }
}
