package com.notes.notes;

import com.notes.notes.controller.NoteController;
import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteService noteService;

    @WithMockUser(username = "testUser")
    @Test
    void newApiNote_shouldReturnSavedNote() throws Exception {

        Note savedNote = new Note();
        savedNote.setId(1L);

        Mockito.when(noteService.createFor(any(Note.class), eq("testUser")))
                .thenReturn(savedNote);

        mockMvc.perform(post("/notes/create")
                        .param("title", "Note title")
                        .param("description", "Note test")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}



