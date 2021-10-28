package com.example.Books.web;

import com.example.Books.model.dto.BookDTO;
import com.example.Books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.bookService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<BookDTO> book = this.bookService.getBookById(id);

        if (book.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id) {
        this.bookService.deleteBook(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
                                              @RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder builder) {
        Long updatedBook = this.bookService.updateBook(bookDTO, id);

        return updatedBook != null
                ? ResponseEntity.created(builder.path("/books/{id}").buildAndExpand(updatedBook).toUri()).build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder builder) {
        long bookId = this.bookService.createBook(bookDTO);

        URI location = builder.path("/books/{id}").buildAndExpand(bookId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }
}
