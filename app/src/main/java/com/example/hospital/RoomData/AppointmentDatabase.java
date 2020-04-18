package com.example.hospital.RoomData;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AppointmentTable.class}, version = 1)
public abstract class AppointmentDatabase extends RoomDatabase {


    private static AppointmentDatabase instance;
    public abstract AppoinmentDao noteDao();

    public static synchronized AppointmentDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppointmentDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyntask(instance).execute();
        }
    };

    private static class PopulateDbAsyntask extends AsyncTask<Void,Void,Void>{

        private AppoinmentDao appoinmentDao;
        private PopulateDbAsyntask (AppointmentDatabase db){
            appoinmentDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appoinmentDao.insert(new AppointmentTable("a","b",1));
            appoinmentDao.insert(new AppointmentTable("a","b",1));
            appoinmentDao.insert(new AppointmentTable("a","b",1));

            return null;
        }
    }
}
