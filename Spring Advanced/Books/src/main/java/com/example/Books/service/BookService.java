package com.example.Books.service;

import com.example.Books.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(long id);

    void deleteBook(long id);

    long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO, Long bookId);
}
