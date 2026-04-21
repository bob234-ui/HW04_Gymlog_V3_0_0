package com.example.hw04_gymlog_v300;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GymLog.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract GymLogDAO gymLogDAO();
    public abstract UserDao userDao();

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDataBase.class,
                                    "gymlog_db"
                            )
                            .allowMainThreadQueries()
                            .build();

                    seedDefaultUser(INSTANCE);
                }
            }
        }
        return INSTANCE;
    }

    private static void seedDefaultUser(AppDataBase db) {
        User existing = db.userDao().getUserByUsername("admin1");
        if (existing == null) {
            db.userDao().insert(new User("admin1", "admin1"));
        }
    }
}