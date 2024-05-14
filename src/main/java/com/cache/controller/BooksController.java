package com.cache.controller;

import com.cache.domain.Book;
import com.cache.service.BookService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

  Logger logger = Logger.getLogger(BooksController.class.getName());

  private final BookService bookService;

  public BooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public Book addBook(@RequestBody Book book) {
    return bookService.save(book);
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable Integer id) {
    logger.info("Get book with id: " + id);
    return bookService.findById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Integer id) {
    bookService.delete(id);
  }

  @PutMapping
  public Book updateBook(@RequestBody Book book) {
    return bookService.updateName(book);
  }

  @DeleteMapping
  public void deleteAll() {
    bookService.deleteAll();
  }
}
