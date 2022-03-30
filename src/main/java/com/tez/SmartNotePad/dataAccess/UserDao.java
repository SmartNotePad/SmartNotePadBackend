package com.tez.SmartNotePad.dataAccess;

import com.tez.SmartNotePad.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findUserByMailAndPassword(String mail,String password);
    boolean existsUserByMail(String mail);
    User findUserByMail(String mail);

}
