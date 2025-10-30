package com.notes.notes;

import com.notes.notes.controller.NoteRestController;
import com.notes.notes.model.Note;
import com.notes.notes.repository.NoteRepository;
import com.notes.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteRestControllerTest {

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
        Mockito.when(noteService.CreateNote(Mockito.any(Note.class))).thenReturn(savedNote);

        // Ejecutamos la petición POST y validamos la respuesta
        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newNoteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Note title"))
                .andExpect(jsonPath("$.description").value("Note test"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}
