package tn.esprit.test.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import tn.esprit.test.entity.BMI;
import tn.esprit.test.entity.User;

@Dao
public interface UserDao {

    @Transaction
    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * from User where username like :username")
    User getUser(String username);

    @Query("SELECT * from User")
    List<User> getAll();



}
