package org.VarunSeemar.digital_library.controller;

import org.VarunSeemar.digital_library.adapter.BookAdapter;
import org.VarunSeemar.digital_library.entities.input.BookInputEntity;
import org.VarunSeemar.digital_library.model.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookAdapter bookAdapter;

    @InjectMocks
    private BookController controller;

    @Test
    void testAddBook(){
        BookInputEntity input = BookInputEntity.builder()
                .id(1L)
                .author("Varun")
                .description("Good Book")
                .publishedDate(Instant.now())
                .name("Varun's Book")
                .build();

        BookModel bookModel = BookModel.builder()
                .id(5L)
                .author("dads")
                .description("steadfast dword")
                .createdAt(Instant.now())
                .publishedDate(Instant.now())
                .name("steadfast")
                .updatedAt(null)
                .build();

        Mockito.when(this.bookAdapter.save(input)).thenReturn(bookModel);

        ResponseEntity<?> actualResponse = this.controller.addBook(input);

        Assertions.assertEquals(bookModel,actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.CREATED,actualResponse.getStatusCode());

    }

}
