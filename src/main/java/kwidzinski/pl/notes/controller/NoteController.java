package kwidzinski.pl.notes.controller;

import kwidzinski.pl.notes.model.Note;
import kwidzinski.pl.notes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String listNotes(Model model) {
        final List<Note> allNotes = noteService.findAllNotes();
        model.addAttribute("allNotes", allNotes);
        return "note-list";
    }

    @GetMapping("/add")
    public String addNote(Model model, Note note) {
        model.addAttribute("note",note);
        return "note-form";
    }

    @PostMapping("/add")
    public String addNote(Note note) {
        noteService.addNote(note);
        return "redirect:/notes/list";
    }

    @GetMapping("/edit/{id}")
    public String editNote(Model model, @PathVariable(name = "id") Long noteId) {
        Optional<Note> noteOptional = noteService.findById(noteId);
        if (noteOptional.isPresent()){
            Note foundNote = noteOptional.get();
            model.addAttribute("note", foundNote);
            return "note-form";
        }
        return "redirect:/notes/list";
    }

    @GetMapping("/delete/{note_id}")
    public String deleteNote(@PathVariable(name = "note_id") Long noteId) {
        noteService.deleteById(noteId);
        return "redirect:/notes/list";
    }
}
