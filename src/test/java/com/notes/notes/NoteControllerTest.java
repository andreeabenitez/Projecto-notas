package com.notes.notes;

import com.notes.notes.controller.NoteController;
import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService; // Será nuestro mock inyectado

    // Bean mock del servicio para Spring
    @TestConfiguration
    static class TestConfig {
        @Bean
        public NoteService noteService() {
            return Mockito.mock(NoteService.class);
        }
    }

    @Test
    void newApiNote_shouldReturnSavedNote() throws Exception {
        String newNoteJson = "{\"title\": \"Note title\",\"description\": \"Noteeeeeee2 test\", \"completed\":false}";

        // Usamos CreateNote en lugar de saveNote
        Note savedNote = new Note(1L, "Note title", "Note test", false);
        Mockito.when(noteService.createNote(Mockito.any(Note.class))).thenReturn(savedNote);

        // Ejecutamos la petición POST y validamos la respuesta
        mockMvc.perform(post("/notes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newNoteJson))
                .andExpect(status().is3xxRedirection());
    }
}
