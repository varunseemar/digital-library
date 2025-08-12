package org.VarunSeemar.digital_library.entities.input;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

@Data
@Getter
@Builder
public class BookInputEntity {
    private long id;
    private String name;
    private String author;
    private String description;
    private Instant publishedDate;
}
