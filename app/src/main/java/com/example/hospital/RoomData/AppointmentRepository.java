package com.example.hospital.RoomData;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;

public class AppointmentRepository {
    private static final String TAG = "MYNoteRepository";
    private AppoinmentDao appoinmentDao;
    private LiveData<List<AppointmentTable>> allNotes;

    public AppointmentRepository(Application application){
        AppointmentDatabase database = AppointmentDatabase.getInstance(application);
        appoinmentDao = database.noteDao();
        allNotes = appoinmentDao.getAllNotes();
    }

    public void insert(AppointmentTable appointmentTable){
        Log.d(TAG, "insert: inside respository");
        new InsertNoteAsyntask(appoinmentDao).execute(appointmentTable);
    }

    public void update(AppointmentTable appointmentTable){
        new UpdateNoteAsyntask((appoinmentDao)).execute(appointmentTable);
    }
    public void delete(AppointmentTable appointmentTable){
        new DeleteNoteAsyntask(appoinmentDao).execute(appointmentTable);
    }
    public void deleteAllNotes(){

        new DeleteAllNotesAsyntask(appoinmentDao).execute();
    }
    public LiveData<List<AppointmentTable>> getAllNotes(){

        return allNotes;
    }

    private static class InsertNoteAsyntask extends AsyncTask<AppointmentTable,Void , Void>{
        private AppoinmentDao appoinmentDao;
        private InsertNoteAsyntask (AppoinmentDao appoinmentDao){
            this.appoinmentDao = appoinmentDao;
        }
        @Override
        protected Void doInBackground(AppointmentTable... appointmentTables) {
            Log.d(TAG, "doInBackground: asyntask");
            appoinmentDao.insert(appointmentTables[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyntask extends AsyncTask<AppointmentTable,Void , Void>{
        private AppoinmentDao appoinmentDao;
        private UpdateNoteAsyntask (AppoinmentDao appoinmentDao){
            this.appoinmentDao = appoinmentDao;
        }
        @Override
        protected Void doInBackground(AppointmentTable... appointmentTables) {
            appoinmentDao.update(appointmentTables[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyntask extends AsyncTask<AppointmentTable,Void , Void>{
        private AppoinmentDao appoinmentDao;
        private DeleteNoteAsyntask (AppoinmentDao appoinmentDao){
            this.appoinmentDao = appoinmentDao;
        }
        @Override
        protected Void doInBackground(AppointmentTable... appointmentTables) {
            appoinmentDao.delete(appointmentTables[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyntask extends AsyncTask<Void, Void , Void>{

        private AppoinmentDao appoinmentDao;

        private DeleteAllNotesAsyntask (AppoinmentDao appoinmentDao){
            this.appoinmentDao = appoinmentDao;
        }

        @Override
        protected Void doInBackground(Void ...notes) {
          appoinmentDao.deleteAllNotes();
            return null;
        }
    }

}
