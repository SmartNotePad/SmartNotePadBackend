package com.tez.SmartNotePad.dataAccess;

import com.tez.SmartNotePad.entities.concretes.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentDao extends JpaRepository<Content,Integer> {

    List<Content> findAllByNote_NoteId(int id);
}
