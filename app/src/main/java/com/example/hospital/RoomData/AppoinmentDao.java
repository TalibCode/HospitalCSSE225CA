package com.example.hospital.RoomData;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppoinmentDao {

    @Insert
    void insert(AppointmentTable appointmentTable);

    @Update
    void update(AppointmentTable appointmentTable);

    @Delete
    void delete(AppointmentTable appointmentTable);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();


    @Query("Select * From note_table Order by priority DESC")
    LiveData<List<AppointmentTable>> getAllNotes();
}
