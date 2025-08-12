package org.VarunSeemar.digital_library.adapter;

import org.VarunSeemar.digital_library.commons.CommonAdapter;
import org.VarunSeemar.digital_library.entities.input.BookInputEntity;
import org.VarunSeemar.digital_library.mappers.input.BookInputMapper;
import org.VarunSeemar.digital_library.model.BookModel;
import org.VarunSeemar.digital_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookAdapter implements CommonAdapter<BookInputEntity ,BookModel> {
    private final BookInputMapper bookInputMapper;
    private final BookService bookService;

    @Autowired
    public BookAdapter(BookInputMapper bookInputMapper, BookService bookService) {
        this.bookInputMapper = bookInputMapper;
        this.bookService = bookService;
    }

    public BookModel save(BookInputEntity bookInputEntity){
        return this.bookService.addBook(this.bookInputMapper.mapToModel(bookInputEntity));
    }

    @Override
    public List<BookModel> getAll() {
        return this.bookService.getAllBooks();
    }

    public BookModel findById(long id){
        return this.bookService.getBookById(id);
    }

    @Override
    public BookModel update(BookInputEntity bookInputEntity){
        return this.bookService.updateBook(this.bookInputMapper.mapToModel(bookInputEntity));
    }

    public boolean deleteById(long id){
        return this.bookService.deleteBookById(id);
    }
}
