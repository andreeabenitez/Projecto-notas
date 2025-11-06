package com.notes.notes.controller;

import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ModelAttribute("note")
    public Note newNote() {
        return new Note();
    }

    @GetMapping("")
    public String listNotes(Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("notes", noteService.findAllFor(authUser.getUsername()));
        return "index";
    }

    @GetMapping("/list")
    public String listNotesAlias(Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("notes", noteService.findAllFor(authUser.getUsername()));
        return "index";
    }

    @GetMapping("/{id}")
    public String viewNote(@PathVariable Integer id, Model model, @AuthenticationPrincipal User authUser) {
        var note = noteService.findByIdFor(id, authUser.getUsername()).orElse(null);
        model.addAttribute("note", note);
        return "redirect:/notes";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute("note") Note note, @AuthenticationPrincipal User authUser) {
        noteService.createFor(note, authUser.getUsername());
        return "redirect:/notes";
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Integer id, @ModelAttribute("note") Note updatedNote, @AuthenticationPrincipal User authUser) {
        noteService.updateFor(id, updatedNote, authUser.getUsername());
        return "redirect:/notes";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Integer id, @AuthenticationPrincipal User authUser) {
        noteService.deleteByIdFor(id, authUser.getUsername());
        return "redirect:/notes";
    }
}
