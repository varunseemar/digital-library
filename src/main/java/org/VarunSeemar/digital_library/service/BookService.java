package org.VarunSeemar.digital_library.service;

import org.VarunSeemar.digital_library.model.BookModel;
import org.VarunSeemar.digital_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook (BookModel book){
        return this.bookRepository.save(book);
    }

    public BookModel getBookById(long id){
        return this.bookRepository.findById(id);
    }

    public BookModel updateBook(BookModel book){
        return this.bookRepository.update(book);
    }

    public boolean deleteBookById(long id){
        return this.bookRepository.delete(id);
    }

    public List<BookModel> getAllBooks(){
        return this.bookRepository.findAll();
    }
}
