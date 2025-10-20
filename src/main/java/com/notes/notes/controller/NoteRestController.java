package com.notes.notes.controller;

import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {
    private NoteService noteService;

    @Autowired
    public NoteRestController(NoteService noteService) {
            this.noteService = noteService;
        }

        @GetMapping
        public ResponseEntity<List<Note>> obtainAll() {
            List<Note> tareas = noteService.obtainAll();
            return ResponseEntity.ok(tareas);
    }
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note newNote = noteService.CreateNote(note);
        return ResponseEntity.ok(newNote);
    }
    /*  @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizar(@PathVariable Long id, @RequestBody Nota nota) {
        Nota actualizada = notaService.actualizar(id, nota);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
     */

}
