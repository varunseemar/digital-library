package org.VarunSeemar.digital_library.controller;

import org.VarunSeemar.digital_library.adapter.BookAdapter;
import org.VarunSeemar.digital_library.entities.input.BookInputEntity;
import org.VarunSeemar.digital_library.model.BookModel;
import org.VarunSeemar.digital_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookAdapter bookAdapter;

    @Autowired
    public BookController(BookAdapter bookAdapter) {
        this.bookAdapter = bookAdapter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookInputEntity book){
        return new ResponseEntity<>(this.bookAdapter.save(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable long id){
        BookModel book = this.bookAdapter.findById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks(){
        List<BookModel> books = this.bookAdapter.getAll();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody BookInputEntity bookInput) {
        BookModel updatedBook = this.bookAdapter.update(bookInput);
        if (updatedBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book not found");
        }
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        boolean deleted = this.bookAdapter.deleteById(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }
        return ResponseEntity.ok("Book deleted successfully");
    }


}
