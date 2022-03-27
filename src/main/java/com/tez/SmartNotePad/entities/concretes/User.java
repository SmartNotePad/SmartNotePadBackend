package com.tez.SmartNotePad.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "ownerUser",fetch =FetchType.LAZY,cascade = CascadeType.DETACH)
    private List<Note> myNotes;

    @ManyToMany(fetch =FetchType.LAZY)
    private List<Note>  sharedNotes;

}
