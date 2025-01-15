package com.lemkor.springboot.LibraryProjectBoot.services;

import com.lemkor.springboot.LibraryProjectBoot.models.Book;
import com.lemkor.springboot.LibraryProjectBoot.models.Person;
import com.lemkor.springboot.LibraryProjectBoot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> findBooksByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);

        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            List<Book> books = person.get().getBooks();
            Date currentDate = new Date();

            for(Book book : books){

                Date bookTakenAt = book.getTakenAt();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bookTakenAt);
                calendar.add(Calendar.DAY_OF_YEAR, 10);
                Date overdueDate = calendar.getTime();

                if(currentDate.after(overdueDate)){
                    book.setOverdue(true);
                }
            }
            return books;

        }else{
            return Collections.emptyList();
        }
    }
}
