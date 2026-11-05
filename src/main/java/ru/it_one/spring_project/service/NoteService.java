package ru.it_one.spring_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.it_one.spring_project.model.NoteDTO;
import ru.it_one.spring_project.model.NoteModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    private List<NoteDTO> notes = new ArrayList<>();

    public List<NoteDTO> getNotes(){
        return notes;
    }

    public NoteDTO createNote(@RequestBody NoteModel note){
        var newNote = new NoteDTO()
                .setHeader(note.getHeader())
                .setText(note.getText())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        notes.add(newNote);

        return newNote;
    }

    public NoteDTO updateNote(UUID id, NoteModel noteData){
        var note = notes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        return note
                .setHeader(noteData.getHeader())
                .setText(noteData.getText())
                .setUpdatedAt(LocalDateTime.now());
    }
}
