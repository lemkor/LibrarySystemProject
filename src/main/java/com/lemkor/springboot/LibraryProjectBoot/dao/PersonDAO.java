package com.lemkor.springboot.LibraryProjectBoot.dao;

import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
//    }
//
//    public Person find(int id) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new PersonMapper(), id).stream().findAny().orElse(null);
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE person SET forename=?, surname=?, age=? WHERE id=?",
//                updatedPerson.getForename(), updatedPerson.getSurname(), updatedPerson.getAge(), id);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO person (forename, surname, age) VALUES (?, ?, ?)", person.getForename(), person.getSurname(), person.getAge());
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
//    }

}
