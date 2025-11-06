package com.notes.notes.service;

import com.notes.notes.model.AppUser;
import com.notes.notes.model.Note;
import com.notes.notes.repository.AppUserRepository;
import com.notes.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final AppUserRepository userRepository;

    public NoteService(NoteRepository noteRepository, AppUserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> findAllFor(String username) {
        return noteRepository.findByOwnerUsername(username);
    }

    public Optional<Note> findByIdFor(Integer id, String username) {
        return noteRepository.findByIdAndOwnerUsername(id, username);
    }

    public Note createFor(Note note, String username) {
        AppUser owner = userRepository.findByUsername(username).orElseThrow();
        note.setOwner(owner);
        return noteRepository.save(note);
    }

    public Note updateFor(Integer id, Note updated, String username) {
        Note n = noteRepository.findByIdAndOwnerUsername(id, username).orElseThrow();
        n.setTitle(updated.getTitle());
        n.setDescription(updated.getDescription());
        return noteRepository.save(n);
    }

    public void deleteByIdFor(Integer id, String username) {
        Note n = noteRepository.findByIdAndOwnerUsername(id, username).orElseThrow();
        noteRepository.delete(n);
    }
}

