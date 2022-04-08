package com.tez.SmartNotePad.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User ownerUser;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    @ManyToMany
    private List<User> participantUsers=new ArrayList<>();

    @OneToMany(mappedBy = "note",fetch = FetchType.LAZY)
    private List<Content> contents=new ArrayList<>();


}
