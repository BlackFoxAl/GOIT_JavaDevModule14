package module14.newspingbootproject.service;

import module14.newspingbootproject.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService{

    private final Map<Long, Note> notesMap = new HashMap<>();

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(notesMap.values());
    }

    @Override
    public Note add(Note note) {
        long randomNoteId = UUID
                .randomUUID()
                .getMostSignificantBits() & Long.MAX_VALUE;
        note.setId(randomNoteId);
        notesMap.put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if (!notesMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not exist");
        }
        notesMap.remove(id);
    }

    @Override
    public void update(Note note) {
        long id = note.getId();
        if (!notesMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not exist");
        }
        notesMap.put(id, note);
    }

    @Override
    public Note getById(long id) {
        if (!notesMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not exist");
        }
        return notesMap.get(id);
    }
}
