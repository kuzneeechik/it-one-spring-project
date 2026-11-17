package ru.it_one.spring_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.it_one.spring_project.model.api.NoteCreateModel;
import ru.it_one.spring_project.model.api.NoteDTO;
import ru.it_one.spring_project.model.domain.NoteEntity;
import ru.it_one.spring_project.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Flux<NoteDTO> getNotes() {
        var notes = noteRepository.findAll();

        return notes
                .map(note -> new NoteDTO()
                        .setId(note.getId())
                        .setHeader(note.getHeader())
                        .setText(note.getText())
                        .setCreatedAt(note.getCreatedAt())
                        .setUpdatedAt(note.getUpdatedAt())
                );
    }

    public Mono<NoteDTO> createNote(@RequestBody NoteCreateModel note) {
        var newNote = new NoteEntity()
                .setHeader(note.getHeader())
                .setText(note.getText())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        return noteRepository.save(newNote)
                .map(savedNote -> new NoteDTO()
                        .setId(savedNote.getId())
                        .setHeader(savedNote.getHeader())
                        .setText(savedNote.getText())
                        .setCreatedAt(savedNote.getCreatedAt())
                        .setUpdatedAt(savedNote.getUpdatedAt()));
    }

    public Mono<NoteDTO> updateNote(UUID id, NoteCreateModel noteData) {
        return noteRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new IllegalArgumentException("Note not found!"))
                )
                .flatMap(note -> {
                    note
                        .setHeader(noteData.getHeader())
                        .setText(noteData.getText())
                        .setUpdatedAt(LocalDateTime.now());

                    return noteRepository.save(note);
                })
                .map(savedNote -> new NoteDTO()
                        .setId(savedNote.getId())
                        .setHeader(savedNote.getHeader())
                        .setText(savedNote.getText())
                        .setCreatedAt(savedNote.getCreatedAt())
                        .setUpdatedAt(savedNote.getUpdatedAt()));
    }
}
