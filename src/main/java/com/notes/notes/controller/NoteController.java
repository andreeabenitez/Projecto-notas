package com.notes.notes.controller;

import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
            this.noteService = noteService;
        }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("notes", noteService.obtainAll());
        model.addAttribute("note", new Note()); // para el formulario
        return "index";
    }

    // --- POST: Crear una nueva nota ---
    @PostMapping("/create")
    public String createNote(@ModelAttribute("note") Note note) {
        noteService.createNote(note);
        return "redirect:/notes/index";
    }

    // --- POST (simulando PUT): Actualizar una nota ---
    @PutMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("note") Note noteDetails) {
        noteService.updateNote(id, noteDetails);
        return "redirect:/notes/index";
    }

    // --- GET (simulando DELETE): Eliminar una nota ---
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes/index";
    }
}
