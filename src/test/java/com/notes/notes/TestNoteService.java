package com.notes.notes;

import com.notes.notes.model.Note;
import com.notes.notes.repository.NoteRepository;
import com.notes.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void obtainAllNotes() {
        //Arrange (Preparar)
        Note note1 = new Note();
        note1.setDescription("Description 1");
        Note note2 = new Note();
        note2.setDescription("Description 2");

        List<Note> testNotes = Arrays.asList(note1, note2);

        when (noteRepository.findAll()).thenReturn(testNotes);

        // Act (Actuar)
        List<Note> result = noteService.obtainAll();

        // Assert (Verificar / comparar rdo esperado rdo obtenido)

        assertEquals(2, result.size());
        assertEquals("Description 1", result.get(0).getDescription());
        assertEquals("Description 2", result.get(1).getDescription());



    }

}
