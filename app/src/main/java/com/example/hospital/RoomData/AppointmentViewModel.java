package com.example.hospital.RoomData;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AppointmentViewModel extends AndroidViewModel {
    private static final String TAG = "MYNoteViewModel";
    private AppointmentRepository repository;
    private LiveData<List<AppointmentTable>> allNotes;


    public AppointmentViewModel(@NonNull Application application) {
        super(application);
        repository = new AppointmentRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert( AppointmentTable nOte){
        Log.d(TAG, "insert:");
        repository.insert(nOte);
    }
    public void update( AppointmentTable nOte){
        repository.update(nOte);
    }

    public void delete( AppointmentTable nOte){
        repository.delete(nOte);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<AppointmentTable>> getAllNotes(){
            return allNotes;
    }



}
