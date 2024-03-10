package module15.newspingbootproject.controllers;

import module15.newspingbootproject.model.Note;
import module15.newspingbootproject.service.NoteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteServiceImpl noteService;
    public NoteController(NoteServiceImpl noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView listAllNotes() {
        ModelAndView result = new ModelAndView("/note/list");
        List<Note> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNoteById(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNoteForm(@RequestParam("id") long id) {
        ModelAndView result = new ModelAndView("/note/edit");
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/edit")
    public ModelAndView editNoteSubmit(@ModelAttribute("note") Note note) {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/add")
    public ModelAndView addNoteForm() {
        return new ModelAndView("/note/add", "note", new Note());
    }

    @PostMapping("/add")
    public ModelAndView addNoteSubmit(@ModelAttribute("note") Note note) {
        noteService.add(note);
        return new ModelAndView("redirect:/note/list");
    }
}