package kwidzinski.pl.notes.service;

import kwidzinski.pl.notes.model.Note;
import kwidzinski.pl.notes.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class NoteServiceUnitTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    private void prepare_data() {
        Note note1 = new Note("Title 1", "Description 1", LocalDate.of(2020,5,25));
        note1.setId(1L);
        Note note2 = new Note("Title 2", "Description 2", LocalDate.of(2021,1,25));
        note2.setId(2L);
        List<Note> testNotes = Arrays.asList(note1, note2);
        Mockito.when(noteRepository.findAll()).thenReturn(testNotes);
    }

    @Test
    void should_find_all_notes() {
        //given
        prepare_data();

        //when
        List<Note> allNotes = noteService.findAllNotes();

        Assertions.assertEquals(1L, allNotes.get(0).getId());
        Assertions.assertEquals("Title 1", allNotes.get(0).getTitle());
        Assertions.assertEquals("Description 1", allNotes.get(0).getDescription());
        Assertions.assertEquals(LocalDate.of(2020,5,25), allNotes.get(0).getDate());
    }

    @Test
    void addNote() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }
}
