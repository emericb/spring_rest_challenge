package com.example.spring_rest_challenge.controller;

import com.example.spring_rest_challenge.entity.Book;
import com.example.spring_rest_challenge.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/index")
    public @ResponseBody List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public @ResponseBody Optional<Book> getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    @PostMapping("/books")
    public @ResponseBody Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/books/search")
    public @ResponseBody List<Book> search(@RequestBody Map<String, String> body) {
        String book = body.get("text");
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(book, book);
    }

    @PutMapping("/books/{id}")
    public @ResponseBody Book update(@PathVariable(value = "id") Long bookId, @RequestBody Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setDescription(book.getDescription());
            return bookRepository.save(existingBook);
        } else {
            return bookRepository.save(book);
        }
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable(value = "id") Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
