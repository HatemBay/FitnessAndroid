package tn.esprit.test.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import tn.esprit.test.Converter;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "id_fkUser", onDelete = CASCADE),indices = {@Index(value = {"id_fkUser"})})
public class BMI implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private int id_fkUser;
    @ColumnInfo
    private double value;
    @ColumnInfo
    private String date;

    public BMI(int id_fkUser, double value, String date) {
        this.id_fkUser = id_fkUser;
        this.value = value;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_fkUser() {
        return id_fkUser;
    }

    public void setId_fkUser(int id_fkUser) {
        this.id_fkUser = id_fkUser;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
