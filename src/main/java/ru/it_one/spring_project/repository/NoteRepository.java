package ru.it_one.spring_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.it_one.spring_project.model.domain.NoteEntity;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteEntity, UUID> {
}
