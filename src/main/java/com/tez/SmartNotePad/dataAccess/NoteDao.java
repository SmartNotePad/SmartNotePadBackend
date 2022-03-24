package com.tez.SmartNotePad.dataAccess;

import com.tez.SmartNotePad.entities.concretes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<Note,Integer> {
}
