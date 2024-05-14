package com.cache.service;

import com.cache.domain.Book;
import com.cache.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Transactional
  @CachePut(value = "books", key = "#result.id")
  public Book save(Book book) {
    return bookRepository.saveAndFlush(book);
  }

  @Cacheable(value = "books", key = "#id")
  public Book findById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @CacheEvict(value = "books", key = "#id")
  public void delete(Integer id) {
    bookRepository.deleteById(id);
  }

  @CachePut(value = "books", key = "#book.id")
  public Book updateName(Book book) {
    Book bookToUpdate = bookRepository.findById(book.getId()).orElse(null);
    assert bookToUpdate != null;
    bookToUpdate.setTitle(book.getTitle());
    return bookRepository.save(bookToUpdate);
  }

  @CacheEvict(value = "books", allEntries = true)
  public void deleteAll() {
    bookRepository.deleteAll();
  }

}
