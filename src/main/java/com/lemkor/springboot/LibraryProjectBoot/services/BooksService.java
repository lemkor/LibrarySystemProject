package com.lemkor.springboot.LibraryProjectBoot.services;

import com.lemkor.springboot.LibraryProjectBoot.models.Book;
import com.lemkor.springboot.LibraryProjectBoot.models.Person;
import com.lemkor.springboot.LibraryProjectBoot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAllSortedByYear() {

        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findById(int id){
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = findById(id);
        book.setOwner(person);
        book.setTakenAt(new Date());
        booksRepository.save(book);
    }

    @Transactional
    public void unassign(int id) {
        Book book = findById(id);
        book.setOwner(null);
        book.setTakenAt(null);
        booksRepository.save(book);
    }

    public Optional<Person> findOwnerByBookId(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return Optional.ofNullable(book.get().getOwner());
        }else{
            return Optional.empty();
        }

    }

    public List<Book> findAllByTitleStartingWith(String prefix){
        Optional<List<Book>> books = Optional.ofNullable(booksRepository.findAllByTitleStartingWith(prefix));
        return books.orElse(null);

    }

    public List<Book> findAllPage(int page, int itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public List<Book> findAllPageSortByYear(int page, int itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
    }
}
