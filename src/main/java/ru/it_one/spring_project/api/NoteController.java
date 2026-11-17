package ru.it_one.spring_project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.it_one.spring_project.model.api.NoteCreateModel;
import ru.it_one.spring_project.model.api.NoteDTO;
import ru.it_one.spring_project.service.NoteService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<Flux<NoteDTO>> getNotes() {
        return ResponseEntity.ok().body(noteService.getNotes());
    }

    @PostMapping
    public ResponseEntity<Mono<NoteDTO>> createNote(@RequestBody NoteCreateModel note) {
        return ResponseEntity.ok().body(noteService.createNote(note));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Mono<NoteDTO>> updateNote(
            @PathVariable UUID id,
            @RequestBody NoteCreateModel note
    ) {
        return ResponseEntity.ok().body(noteService.updateNote(id, note));
    }
}
