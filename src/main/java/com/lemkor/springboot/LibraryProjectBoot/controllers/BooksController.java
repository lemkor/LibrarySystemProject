package com.lemkor.springboot.LibraryProjectBoot.controllers;


import com.lemkor.springboot.LibraryProjectBoot.models.Book;
import com.lemkor.springboot.LibraryProjectBoot.models.Person;
import com.lemkor.springboot.LibraryProjectBoot.services.BooksService;
import com.lemkor.springboot.LibraryProjectBoot.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String books(@RequestParam(value = "sort_by_year", required = false) boolean sortByYear, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage, Model model) {
        if (page != null && booksPerPage != null) {
            // Pagination with or without sorting
            if (sortByYear) {
                model.addAttribute("books", booksService.findAllPageSortByYear(page, booksPerPage));
            } else {
                model.addAttribute("books", booksService.findAllPage(page, booksPerPage));
            }
        } else {
            // Sorting without pagination, or default listing
            if (sortByYear) {
                model.addAttribute("books", booksService.findAllSortedByYear());
            } else {
                model.addAttribute("books", booksService.findAll());
            }
        }

        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="query", required = false) String query, Model model) {
        if(query != null && !query.isEmpty()) {
            model.addAttribute("found", booksService.findAllByTitleStartingWith(query));
        }
        return "books/search";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findById(id));

        Optional<Person> owner = booksService.findOwnerByBookId(id);

        if(owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        }
        else {
            model.addAttribute("people", peopleService.findAll());
        }

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(id, person);
        return "redirect:/books/" +id;
    }

    @DeleteMapping("/{id}/release")
    public String unassignBook(@PathVariable("id") int id) {
        booksService.unassign(id);
        return "redirect:/books/"+id;
    }
}
