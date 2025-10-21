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
    public Note CreateNote(Note note) {
        return noteRepository.save(note);
    }

    public Note update(Long id, Note note) {
        return noteRepository.findById(id)
                .map(existingNote -> {
                    existingNote.setTitle(note.getTitle());
                    existingNote.setDescription(note.getDescription());
                    return noteRepository.save(existingNote);
                })
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    }



