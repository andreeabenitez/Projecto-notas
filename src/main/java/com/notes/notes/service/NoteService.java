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
   /* public Nota actualizar(Long id, Nota nuevaNota) {
        return notaRepository.findById(id).map(nota -> {
            nota.setTitulo(nuevaNota.getTitulo());
            nota.setContenido(nuevaNota.getContenido());
            return notaRepository.save(nota);
        }).orElse(null);
    }

    public void eliminar(Long id) {
        notaRepository.deleteById(id);
    }

    */
}



