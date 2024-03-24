package module16.newspingbootproject.repository;

import module16.newspingbootproject.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
