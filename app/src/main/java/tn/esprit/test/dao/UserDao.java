package tn.esprit.test.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.test.entity.User;

public interface UserDao {
    @Insert
    void insertOne(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * from user_table")
    List<User> getAll();
}
