package com.lemkor.springboot.LibraryProjectBoot.dao;

import com.lemkor.springboot.LibraryProjectBoot.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Person person = new Person();
//
//
//        person.setId(rs.getInt("id"));
//        person.setForename(rs.getString("forename"));
//        person.setSurname(rs.getString("surname"));
//        person.setAge(rs.getInt("age"));
//
//        return person;
        return new Person();
    }
}
