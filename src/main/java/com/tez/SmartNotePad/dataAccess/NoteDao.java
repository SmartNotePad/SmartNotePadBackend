package com.tez.SmartNotePad.dataAccess;

import com.tez.SmartNotePad.entities.concretes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteDao extends JpaRepository<Note,Integer> {
    List<Note> findAllByUserUserId(int id);

}
