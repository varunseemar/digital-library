package org.VarunSeemar.digital_library.model;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Data
@Builder
@With
@Getter
@Setter
public class BookModel {
    private long id;
    private String name;
    private String author;
    private String description;
    private Instant publishedDate;
    private Instant createdAt;
    private Instant updatedAt;
}
