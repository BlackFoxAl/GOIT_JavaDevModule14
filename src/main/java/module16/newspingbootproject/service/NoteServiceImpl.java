package module16.newspingbootproject.service;

import module16.newspingbootproject.model.Note;
import module16.newspingbootproject.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) {
        if (!noteRepository.existsById(note.getId())) {
            throw new IllegalArgumentException("Note with id " + note.getId() + " not exist");
        }
        noteRepository.save(note);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException("Note with id " + id + " not exist")
                );
    }
}
