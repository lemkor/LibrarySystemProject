package com.lemkor.springboot.LibraryProjectBoot.repositories;

import com.lemkor.springboot.LibraryProjectBoot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTitleStartingWith(String prefix);
}
