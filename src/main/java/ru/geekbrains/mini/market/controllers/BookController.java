package ru.geekbrains.mini.market.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.dto.BookDto;
import ru.geekbrains.mini.market.entites.Book;
import ru.geekbrains.mini.market.exceptions.MarketError;
import ru.geekbrains.mini.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Api("Set of endpoints for books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation("Returns all books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // http://localhost:8189/market/api/v1/books/1
    @GetMapping("/{id}")
    @ApiOperation("Returns a specific book by their identifier. 404 if does not exist.")
    public BookDto getBookById(@ApiParam("Id of the book to be obtained. Cannot be empty.") @PathVariable Long id) {
        Book b = bookService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id: " + id));
        return new BookDto(b);
    }

    // http://localhost:8189/market/api/v1/books/
    @PostMapping
    @ApiOperation("Creates a new book. If id != null, then throw bad request response.")
    public ResponseEntity<?> createNewBook(@RequestBody Book book) {
        if (book.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    // http://localhost:8189/market/api/v1/books/
    @PutMapping
    @ApiOperation("Modify book")
    public ResponseEntity<?> modifyBook(@RequestBody Book book) {
        if (book.getId() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be not null for new entity"), HttpStatus.BAD_REQUEST);
        }
        if (!bookService.existsById(book.getId())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Book with id: " + book.getId() + " doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    // http://localhost:8189/market/api/v1/books/1
    @DeleteMapping("/{id}")
    @ApiOperation("Delete book")
    public void deleteById(@ApiParam("Id of the book") @PathVariable Long id) {
        bookService.deleteById(id);
    }
}