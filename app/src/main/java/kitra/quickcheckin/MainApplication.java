package kitra.quickcheckin;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import kitra.quickcheckin.data.local.AppDatabase;

public class MainApplication extends Application {

    private static AppDatabase database;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            synchronized (AppDatabase.class) {
                database = Room.databaseBuilder(context, AppDatabase.class, "app_database").fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
