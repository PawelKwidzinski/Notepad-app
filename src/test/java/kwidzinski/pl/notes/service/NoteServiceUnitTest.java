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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class NoteServiceUnitTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private List<Note> testNoteList() {
        Note note1 = new Note("Title 1", "Description 1", LocalDate.of(2020, 5, 25));
        note1.setId(1L);
        Note note2 = new Note("Title 2", "Description 2", LocalDate.of(2021, 1, 25));
        note2.setId(2L);
        return Arrays.asList(note1, note2);
    }

    @Test
    void should_return_all_notes() {
        //given
        List<Note> testNotes = testNoteList();
        given(noteService.findAllNotes()).willReturn(testNotes);

        //when
        List<Note> expectedNotes = noteService.findAllNotes();

        //then
       assertEquals(expectedNotes, testNotes);
       verify(noteRepository).findAll();
    }

    @Test
    void should_add_given_note() {
        // given
        Note note3 = new Note("Title 3", "Description 3", LocalDate.of(2005, 11, 25));
        note3.setId(3L);

        // when
        when(noteRepository.save(any(Note.class))).thenReturn(note3);

        // then
        Optional<Note> actual = noteService.addNote(note3);
        Assertions.assertSame(note3, actual.get());
        verify(noteRepository).save(note3);
    }

    @Test
    void should_find_note_by_id() {
        // given
        Note note4 = new Note("Title 4", "Description 4", LocalDate.of(2008, 5, 25));
        note4.setId(4L);

        // when
        when(noteRepository.findById(4L)).thenReturn(Optional.of(note4));

        // then
        final Optional<Note> actual = noteService.findById(4L);
        Assertions.assertEquals(4L, actual.get().getId());
        verify(noteRepository).findById(note4.getId());
    }

    @Test
    void should_delete_note() {
        // given
        Note note5 = new Note("Title 5", "Description 5", LocalDate.of(2000, 4, 20));
        note5.setId(5L);

        // when
        when(noteRepository.findById(note5.getId())).thenReturn(Optional.of(note5));

        // then
        noteService.deleteById(note5.getId());
        verify(noteRepository).deleteById(note5.getId());
    }
}
