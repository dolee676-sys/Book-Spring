package com.example.borrow.controller;

import com.example.borrow.entity.Book;
import com.example.borrow.service.BookService;
import com.example.borrow.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/books")
public class BookController {
        private final BookService bookService;
        public BookController(BookService bookService){
            this.bookService = bookService;
        }

        @GetMapping
        public List<Book> list(@RequestParam(required = false)
            String title){
            return bookService.list(title);
            }




        @GetMapping("/{id}")
    public Book get(@PathVariable Long id) throws NotFoundException {
            return bookService.getById(id);
        }

        @PostMapping
    public Book create(@RequestBody Book book){
            return bookService.create(book);
        }

        @PutMapping("/{id}")
    public Book update(@PathVariable Long id,@RequestBody Book book) throws NotFoundException {
            return bookService.update(id,book);
        }
        @GetMapping("/{id}/borrowers")
    public List<Map<String, Object>> borrowers(
            @PathVariable Long id) throws NotFoundException {
            return bookService.borrowers(id);
    }


}






































