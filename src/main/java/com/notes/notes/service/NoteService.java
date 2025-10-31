package com.notes.notes.service;

import com.notes.notes.model.Note;
import com.notes.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public List<Note> obtainAll(){
        return noteRepository.findAll();
    }
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));
        existingNote.setTitle(noteDetails.getTitle());
        existingNote.setDescription(noteDetails.getDescription());

        return noteRepository.save(existingNote);
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Nota no encontrada con ID: " + id);
        }
        noteRepository.deleteById(id);
    }
}



