package org.VarunSeemar.digital_library.repository;

import org.VarunSeemar.digital_library.entities.output.BookOutputEntity;
import org.VarunSeemar.digital_library.mappers.output.BookOutputMapper;
import org.VarunSeemar.digital_library.model.BookModel;
import org.VarunSeemar.digital_library.repository.jpa.BookJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookRepository {

    private final BookJPARepository bookJPARepository;
    private final BookOutputMapper bookOutputMapper;


    @Autowired
    public BookRepository(BookJPARepository bookJPARepository,BookOutputMapper bookOutputMapper){
        this.bookJPARepository = bookJPARepository;
        this.bookOutputMapper = bookOutputMapper;
    }

    public BookModel save(BookModel book){
        BookOutputEntity outputEntity = this.bookOutputMapper.mapFromModel(book);
        BookOutputEntity savedOutputEntity = this.bookJPARepository.save(outputEntity);
        return this.bookOutputMapper.mapToModel(savedOutputEntity);
    }

    public BookModel findById(long id){
        Optional<BookOutputEntity> optionalBookOutputEntity = this.bookJPARepository.findById(id);
        return optionalBookOutputEntity.map(this.bookOutputMapper::mapToModel).orElse(null);
    }

    public BookModel update(BookModel book){

        BookModel bookModel = this.findById(book.getId());
        book.setId(bookModel.getId());
        book.setUpdatedAt(Instant.now());
        book.setCreatedAt(bookModel.getCreatedAt());
        return this.save(book);
//        Optional<BookOutputEntity> outputEntity = this.bookJPARepository.findById(id);
//        if(outputEntity.isPresent()){
//            Optional<BookModel> bookModel = outputEntity.map(this.bookOutputMapper::mapToModel);
//            bookModel.
//        }
//        else{
//            return null;
//        }
//        BookOutputEntity outputEntity = this.bookOutputMapper.mapFromModel(book);
//        BookOutputEntity savedOutputEntity = this.bookJPARepository.save(outputEntity);
//        return this.bookOutputMapper.mapToModel(savedOutputEntity);
    }

    public boolean delete(long id){
        if(this.bookJPARepository.existsById(id)){
            this.bookJPARepository.deleteById(id);
            return true;
        }
            return false;
    }

    public List<BookModel> findAll(){
        List<BookOutputEntity> entities = this.bookJPARepository.findAll();
        return entities.stream().map(this.bookOutputMapper::mapToModel).collect(Collectors.toList());
    }
}
