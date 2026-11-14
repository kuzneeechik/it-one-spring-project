package ru.it_one.spring_project.model.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class NoteDTO {

    private UUID id;

    private String header;

    private String text;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
