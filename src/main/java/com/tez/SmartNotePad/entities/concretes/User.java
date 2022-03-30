package com.tez.SmartNotePad.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name_surname")
    private String nameSurname;

    @Column(name = "user_mail")
    private String mail;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "ownerUser",cascade = CascadeType.DETACH)
    private List<Note> myNotes=new ArrayList<>();

    @ManyToMany(mappedBy = "participantUsers",cascade = CascadeType.DETACH)
    private List<Note> sharedNotes=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.DETACH)
    private List<Content> contents=new ArrayList<>();

}
