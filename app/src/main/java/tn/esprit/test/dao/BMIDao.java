package tn.esprit.test.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.test.entity.BMI;

@Dao
public interface BMIDao {

    @Delete
    void deleteBMI(BMI bmi);

    @Query("SELECT * from BMI where id_fkUser like :id")
    List<BMI> getAllBMIs(int id);

    @Query("INSERT INTO BMI (id_fkUser, value, date) VALUES (:uid, :bmi, :date)")
    void addBMI(int uid, double bmi, String date);
}
