package ru.it_one.spring_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.it_one.spring_project.model.api.NoteCreateModel;
import ru.it_one.spring_project.model.api.NoteDTO;
import ru.it_one.spring_project.model.domain.NoteEntity;
import ru.it_one.spring_project.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<NoteDTO> getNotes() {
        var notes = noteRepository.findAll();

        return notes.stream()
                .map(note -> new NoteDTO()
                        .setId(note.getId())
                        .setHeader(note.getHeader())
                        .setText(note.getText())
                        .setCreatedAt(note.getCreatedAt())
                        .setUpdatedAt(note.getUpdatedAt()))
                .toList();
    }

    public NoteDTO createNote(@RequestBody NoteCreateModel note) {
        var newNote = new NoteEntity()
                .setHeader(note.getHeader())
                .setText(note.getText())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        noteRepository.save(newNote);

        return new NoteDTO()
                .setId(newNote.getId())
                .setHeader(newNote.getHeader())
                .setText(newNote.getText())
                .setCreatedAt(newNote.getCreatedAt())
                .setUpdatedAt(newNote.getUpdatedAt());
    }

    public NoteDTO updateNote(UUID id, NoteCreateModel noteData) {
        var note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        note
            .setHeader(noteData.getHeader())
            .setText(noteData.getText())
                .setUpdatedAt(LocalDateTime.now());

        noteRepository.save(note);

        return new NoteDTO()
                .setId(note.getId())
                .setHeader(note.getHeader())
                .setText(note.getText())
                .setUpdatedAt(LocalDateTime.now())
                .setCreatedAt(LocalDateTime.now());
    }
}
