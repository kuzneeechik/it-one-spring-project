package ru.it_one.spring_project.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.it_one.spring_project.model.domain.NoteEntity;

import java.util.UUID;

public interface NoteRepository extends ReactiveCrudRepository<NoteEntity, UUID> {
}
