package com.lemkor.springboot.LibraryProjectBoot.dao;

import org.springframework.stereotype.Component;

@Component
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO book (title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public Book find(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
//    }
//    public List<Book> find(List<Integer> id) {
//        List<Book> books = new ArrayList<>();
//        for (int i = 0; i < id.size(); i++) {
//            books.add(jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), i).stream().findAny().orElse(null));
//        }
//        return books;
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?",
//                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
//    }
//
//    public void assign(int id, Person person) {
//        jdbcTemplate.update("INSERT INTO person_book VALUES (?,?)", person.getId(), id);
//    }
//
//    public List<Book> getBooksForPerson(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book INNER JOIN person_book on book.id = person_book.book_id WHERE person_book.person_id = ?", new BeanPropertyRowMapper<>(Book.class), id);
//    }
//
//    public Optional<Person> getPersonFromBook(int id) {
//        return jdbcTemplate.query("SELECT * FROM person INNER JOIN person_book on person.id = person_book.person_id WHERE person_book.book_id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
//    }
//
//    public void unassign(int id) {
//        jdbcTemplate.update("DELETE FROM person_book WHERE person_book.book_id = ?", id);
//    }
}
