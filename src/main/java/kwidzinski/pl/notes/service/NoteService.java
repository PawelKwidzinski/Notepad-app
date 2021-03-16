package kwidzinski.pl.notes.service;

import kwidzinski.pl.notes.model.Note;
import kwidzinski.pl.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository repository;

    @Autowired
    public NoteService(final NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> findAllNotes() {
        return repository.findAll();
    }

    public Optional<Note> addNote(final Note toSave) {
        toSave.setDate(LocalDate.now());
        return Optional.of(repository.save(toSave));
    }

    public Optional<Note> findById(final Long noteId) {
        return repository.findById(noteId);
    }

    public void deleteById(final Long id){
        Optional<Note> noteToRemove = findById(id);
        repository.deleteById(noteToRemove.get().getId());
    }
}
