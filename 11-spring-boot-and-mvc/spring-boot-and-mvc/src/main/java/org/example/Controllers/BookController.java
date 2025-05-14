package org.example.Controllers;

import org.example.Models.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 1925));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book(3, "The Catcher in the Rye", "J.D. Salinger", 1951));
        books.add(new Book(4, "Beloved", "Toni Morrison", 1987));
        books.add(new Book(5, "The Color Purple", "Alice Walker", 1982));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
