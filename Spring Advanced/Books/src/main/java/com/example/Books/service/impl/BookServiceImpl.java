package com.example.Books.service.impl;

import com.example.Books.model.dto.AuthorDTO;
import com.example.Books.model.dto.BookDTO;
import com.example.Books.model.entity.AuthorEntity;
import com.example.Books.model.entity.BookEntity;
import com.example.Books.repository.AuthorRepository;
import com.example.Books.repository.BookRepository;
import com.example.Books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.
                findAll().
                stream().
                map(this::asBook).
                collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(long id) {
        return bookRepository.
                findById(id).
                map(this::asBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public long createBook(BookDTO bookDTO) {

        AuthorEntity author = authorRepository.
                findByName(bookDTO.getAuthor().getName()).
                orElseGet(() -> new AuthorEntity().setName(bookDTO.getAuthor().getName()));

        BookEntity newBook = new BookEntity().
                setAuthor(author).
                setIsbn(bookDTO.getIsbn()).
                setTitle(bookDTO.getTitle());

        return bookRepository.save(newBook).getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO, Long bookId) {
        AuthorEntity author = authorRepository.
                findByName(bookDTO.getAuthor().getName()).
                orElseGet(() -> new AuthorEntity().setName(bookDTO.getAuthor().getName()));

        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElse(null);
        if (bookEntity == null) {
            return null;
        }

        bookEntity.setTitle(bookDTO.getTitle())
                .setIsbn(bookDTO.getIsbn())
                .setAuthor(author);
        return bookRepository.save(bookEntity).getId();
    }

    private BookDTO asBook(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}